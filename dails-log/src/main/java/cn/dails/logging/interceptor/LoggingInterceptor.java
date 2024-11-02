package cn.dails.logging.interceptor;

import cn.dails.logging.annotation.MyLoggable;
import cn.dails.logging.context.LogContent;
import cn.dails.logging.context.ThreadContext;
import cn.dails.logging.utils.CommonTools;
import cn.dails.logging.utils.LoggingUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * 日志打印拦截
 * 
 * @author dzl
 *
 */
@Aspect
@Service
@Order(100)
public class LoggingInterceptor {
	private static final String _json_serialize_error = "can't_serialize_to_json_string";

	
	@Around(value = "@annotation(com.dails.log.annotation.MyLoggable)")
	public Object intercept(ProceedingJoinPoint point) throws Throwable {
		Logger logger = LogManager.getLogger(point.getTarget().getClass());

		Method method = CommonTools.getMethod(point);
		long startTimeMillis = System.currentTimeMillis();
		long costMillis = 0;
		Object result = null;
		Throwable exception = null;

		try {
			result = point.proceed();
		} catch (Throwable e) {
			exception = e;
		} finally {
			costMillis = System.currentTimeMillis() - startTimeMillis;
		}
		List<Object> inputsStringList = new ArrayList<>(point.getArgs().length);
		for (Object element : point.getArgs()) {
			try {
					inputsStringList.add(JSONObject.toJSONString(element));
					
			} catch (Exception e) {

				inputsStringList.add(_json_serialize_error);
				logger.error(e);
			}

			
		}
		
		LogContent logContent=new LogContent(method, inputsStringList, result, costMillis,exception);
		
		LoggingUtils.log(getLevel(method), logger,logContent,null);
		
		if (exception != null && ThreadContext.get().getStackInfo().isContain(exception, true)){
			LoggingUtils.log(Level.ERROR, logger,logContent, exception);
		}
		
		if (exception != null) {
			throw exception;
		}

		return result;
	}

	private Level getLevel(Method method) {
		MyLoggable loggableAnnotaion = method.getAnnotation(MyLoggable.class);

		// LoggingSupport
		// s=loggableAnnotaion.annotationType().getAnnotation(LoggingSupport.class);
		String logLevel = loggableAnnotaion.level();
		return Level.getLevel(logLevel);
	}

}
