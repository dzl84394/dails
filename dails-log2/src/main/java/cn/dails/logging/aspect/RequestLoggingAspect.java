package cn.dails.logging.aspect;

import cn.dails.logging.LogConf;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {
    private final AtomicInteger totalCounter = new AtomicInteger(0);
    private final ConcurrentHashMap<String, AtomicInteger> interfaceCounters = new ConcurrentHashMap<>();

    @Around("execution(* cn.dails.controller..*.*(..)) && !execution(* cn.dails.controller..*.internal*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();




        StringBuffer requestUrl = request.getRequestURL();
        String queryString = request.getQueryString();
        String fullUri = queryString != null ? requestUrl.append("?").append(queryString).toString() : requestUrl.toString();


        // 构建基础日志对象‌:ml-citation{ref="1,3" data="citationList"}
        JSONObject logData = new JSONObject();
        String traceId = request.getHeader("traceId");
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }
//        logData.put("traceId", traceId);
        MDC.put("X-B3-TraceId", traceId);
//        MDC.put("spanId", spanId);
//        MDC.put("parentSpanId", parentSpanId);
//        MDC.put("sampled", sampled);

        // 处理请求参数‌:ml-citation{ref="4,7" data="citationList"}

        logData.put("uri", fullUri);
        logData.put("httpMethod", request.getMethod());
        logData.put("urlparam", request.getQueryString());  // 直接保留原始参数‌:ml-citation{ref="2" data="citationList"}
        // 通过方法签名直接定位参数类型‌:ml-citation{ref="6" data="citationList"}
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        Object requestBody = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> parameters[Arrays.asList(joinPoint.getArgs()).indexOf(arg)]
                        .isAnnotationPresent(RequestBody.class))
                .findFirst()
                .orElse(null);
        String requestBodyStr = requestBody != null ? JSON.toJSONString(requestBody) : "";
        logData.put("requestbody", truncate(requestBodyStr));

        // 获取目标类的 Class 对象
        Class<?> targetClass = AopUtils.getTargetClass(joinPoint.getTarget());

        // 获取目标方法
        Method method = signature.getMethod();

        // 获取目标类的类名和方法名
        String className = targetClass.getName(); // 完整类名
        String methodName = method.getName();    // 方法名

        // 获取目标类的文件名和行号
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = null;
        int lineNumber = -1;
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().equals(targetClass.getName())) {
                fileName = element.getFileName(); // 获取文件名
                lineNumber = element.getLineNumber(); // 获取行号
                break;
            }
        }
        //"Class":"%logger{50}", "Method":"%file[%method:%line]"
//        "Class": "cn.dails.logging.aspect.RequestLoggingAspect",
//                "Method": "RequestLoggingAspect.java[logRequest:92]",
        MDC.put("classname", className+"["+methodName+":"+lineNumber+"]");
        // 并发统计‌:ml-citation{ref="5" data="citationList"}
        String requestPath = request.getRequestURI();
        logData.put("requestPath", requestPath);
        interfaceCounters.putIfAbsent(requestPath, new AtomicInteger(0));
        AtomicInteger currentCounter = interfaceCounters.get(requestPath);
        logData.put("currentConcurrency", currentCounter.incrementAndGet());
        logData.put("totalConcurrency", totalCounter.incrementAndGet());

        Object result;
        long startTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
            logData.put("output", truncate(JSON.toJSONString(result)));
            return result;
        } catch (Throwable e) {
            logData.put("error", e.getClass().getSimpleName());
            logData.put("errorMsg", truncate(e.getMessage()));
            throw e; // 直接抛出异常，不要做其他处理
        } finally {
            currentCounter.decrementAndGet();
            totalCounter.decrementAndGet();
            logData.put("costTime", System.currentTimeMillis() - startTime);
            logger.info(logData.toJSONString());
        }
    }






    // 通用截断方法‌:ml-citation{ref="5,6" data="citationList"}
    private String truncate(String json) {
        if (json==null){
            return json;
        }
        return json.length() > LogConf.MAX_LENGTH ? json.substring(0, LogConf.MAX_LENGTH) + "..." : json;
    }

    /**
     * 获取或生成 traceId ，
     * X-B3-TraceId，总的
     * X-B3-SpanId  本次
     * X-B3-ParentSpanId  前一个
     */
    private String getOrGenerateTraceId(HttpServletRequest request) {
        // 检查请求头中是否有 traceId
        String traceId = null;

        // 如果没有 traceId，检查 Zipkin 的 X-B3-TraceId
        if (traceId == null || traceId.isEmpty()) {
            traceId = request.getHeader("X-B3-TraceId");
        }

        // 如果仍然没有 traceId，生成一个新的 traceId
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }

        return traceId;
    }

    /**
     * 判断请求来源（前端页面或后端服务）
     */
    private String getRequestSource(HttpServletRequest request) {
        // 通过 Header 判断
        String requestSource = request.getHeader("X-Request-Source");
        if (requestSource != null) {
            return requestSource; // 例如 "Web" 或 "Service"
        }

        // 通过 User-Agent 判断
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.contains("Mozilla")) {
            return "Web"; // 浏览器请求
        }

        // 通过请求路径判断
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/api/")) {
            return "Service"; // API 请求
        }

        // 默认返回 "Unknown"
        return "Unknown";
    }

    public static Method getMethod(JoinPoint point) throws Exception {
        MethodSignature signature = (MethodSignature) point.getSignature();
        final String methodName = point.getSignature().getName();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            method = point.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        }
        return method;
    }

}
