package cn.dails.logging.aspect;

import cn.dails.logging.LogConf;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggableAspect {

    @Around("@annotation(cn.dails.logging.annotation.Loggable)") // 拦截带有 @Logable 注解的方法
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标类的 Logger
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Logger logger = LoggerFactory.getLogger(targetClass);

        // 获取目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取类名和方法名
        String className = targetClass.getSimpleName(); // 类名（简化）
        String methodName = method.getName();          // 方法名

        // 打印入参
        Object[] args = joinPoint.getArgs(); // 获取方法参数
        logger.info("Class: {}, Method: {}, Input: {}", className, methodName, args);
        // 构建基础日志对象‌:ml-citation{ref="1,3" data="citationList"}
        JSONObject logData = new JSONObject();
        logData.put("input",args);
        try {
            // 执行目标方法
            Object result = joinPoint.proceed();

            // 打印出参
//            logger.info("Class: {}, Method: {}, Output: {}", className, methodName, result);
            logData.put("output",truncate(JSONObject.toJSONString(result)));
            logger.info(JSONObject.toJSONString(logData));
            return result;
        } catch (Throwable throwable) {
            // 打印异常
//            logger.error("Class: {}, Method: {}, Error: {}", className, methodName, throwable.getMessage(), throwable);
            logger.info(JSONObject.toJSONString(logData),throwable);
            throw throwable; // 继续抛出异常
        }
    }
    private String truncate(String json) {
        if (json==null){
            return json;
        }
        return json.length() > LogConf.MAX_LENGTH ? json.substring(0, LogConf.MAX_LENGTH) + "..." : json;
    }
}