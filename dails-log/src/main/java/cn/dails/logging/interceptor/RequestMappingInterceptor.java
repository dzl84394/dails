package cn.dails.logging.interceptor;

import cn.dails.base.bean.BaseRequest;
import cn.dails.base.bean.BaseResponse;
import cn.dails.base.exceptions.MyException;
import cn.dails.logging.annotation.LoggingExclude;
import cn.dails.logging.context.LogContent;
import cn.dails.logging.context.ThreadContext;
import cn.dails.logging.utils.CommonTools;
import cn.dails.logging.utils.LoggingUtils;
import com.google.gson.Gson;
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
import java.util.concurrent.atomic.AtomicInteger;

//import org.springframework.boot.actuate.metrics.CounterService;
//import org.springframework.boot.actuate.metrics.GaugeService;
//import org.springframework.boot.actuate.metrics.Metric;
//import org.springframework.boot.actuate.metrics.reader.MetricReader;
;
/**
 * 日志打印拦截
 * 
 * @author dzl
 *
 */
@Aspect
@Service
@Order(100)
public class RequestMappingInterceptor {

	private static final String _json_serialize_error = "can't_serialize_to_json_string";

//	@Autowired
//	GaugeService gaugeService;
//	// counterService有加1，减1功能
//	@Autowired
//	CounterService counterService;
//
//	@Autowired
//	private MetricReader metricReader;

	@Around(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
			"||@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public Object intercept(ProceedingJoinPoint point) throws Throwable {
		
		Logger logger = LogManager.getLogger(point.getTarget().getClass());
		

		Method method = CommonTools.getMethod(point);
		String methodNameStr = method.getDeclaringClass().getName()+"."+method.getName();

		long startTimeMillis = System.currentTimeMillis();
		long costMillis = 0;
		AtomicInteger atomic = ThreadContext.get().getCurrency("total_concurrency");
		atomic.incrementAndGet();
		ThreadContext.get().setCurrency("total_concurrency", atomic);
		
		String methodName = method.getDeclaringClass().getSimpleName() + "#" + method.getName() + "_concurrency";
		
		AtomicInteger atomicMethod = ThreadContext.get().getCurrency(methodName);
		atomicMethod.incrementAndGet();
		ThreadContext.get().setCurrency(methodName, atomicMethod);
		
		
//		counterService.increment("total_concurrency");
//		counterService.increment(method.getDeclaringClass().getSimpleName() + "#" + method.getName() + "_concurrency");
//		Metric<Long> concurrency = (Metric<Long>) metricReader.findOne(
//				"counter." + method.getDeclaringClass().getSimpleName() + "#" + method.getName() + "_concurrency");
//		Metric<Long> totalConcurrency = (Metric<Long>) metricReader.findOne("counter.total_concurrency");

		Object result = null;
		Throwable exception = null;
		try {
			result = point.proceed();
		} catch (MyException e) {
			exception = e;
			if (result==null) {
				BaseResponse base = new BaseResponse();
				base.bindErrorCode(e.getErrorCode());
				base.bindResultDesc(e.getResultDesc());
				base.buildFaild();
				result = base;
			}
		}  catch (Throwable e) {
			exception = e;
			BaseResponse base = new BaseResponse();
//			base.bindErrorCode();
			base.bindResultDesc(e.getMessage());
			base.buildFaild();
			result = base;
			
			
		} finally {
			costMillis = System.currentTimeMillis() - startTimeMillis;
			
		}
//		MethodLogContent logContent = new MethodLogContent(method, point.getArgs(), result, costMillis,
//				concurrency.getValue(), totalConcurrency.getValue(), exception);


//		for(int i=0;i<method.getParameterAnnotations().length;i++){
//			Annotation[] paraAnnotations = method.getParameterAnnotations()[i];
//			if (paraAnnotations==null||paraAnnotations.length==0) continue;
//			for(Annotation annotation:paraAnnotations){
//				if (annotation.annotationType().equals(LoggingExclude.class)){
//					this.inputs[i]="#LoggingExclude#";
//				}
//			}
//		}
//		Annotation[] annotations = method.getDeclaredAnnotations();
//		boolean b = Arrays.stream(annotations).anyMatch(f->f.annotationType().equals(LoggingExclude.class));



//		for (Annotation annotation : annotations) {
////			System.out.println(clazz.getSimpleName().concat(".").concat(method.getName()).concat(".")
////					.concat(annotation.annotationType().getSimpleName()));
//			if (annotation.annotationType().equals(LoggingExclude.class)){
//				LoggingExclude exclude = method.getAnnotation(LoggingExclude.class);
//				exclude.inputExclude();
//				exclude.outputExclude();
//			}
//		}


		LogContent logContent = new LogContent(methodNameStr, null, null, costMillis,
				ThreadContext.get().getCurrency(methodName).get()
				, ThreadContext.get().getCurrency("total_concurrency").get(), exception);;

		LoggingExclude exclude = method.getAnnotation(LoggingExclude.class);
		if (exclude == null || !exclude.inputExclude() ){
			List<Object> inputsStringList = new ArrayList<>(point.getArgs().length);
			Gson gson = new Gson();
//		String userJson = gson.toJson(logContent.getOutput());
			for (Object element : point.getArgs()) {
				try {
					if (element instanceof BaseRequest) {
						inputsStringList.add(gson.toJson(element));
					}
				} catch (Exception e) {
					inputsStringList.add(_json_serialize_error);
					logger.error(e);
				}
			}
			logContent.setInputs(inputsStringList);
		}

		if (exclude == null || !exclude.outputExclude() ){
			logContent.setOutput(result);
		}








		

		LoggingUtils.log(Level.INFO, logger, logContent, null);
		
//		counterService.decrement(method.getDeclaringClass().getSimpleName() + "#" + method.getName() + "_concurrency");
//		counterService.decrement("total_concurrency");

		atomic = ThreadContext.get().getCurrency("total_concurrency");
		atomic.decrementAndGet();
		ThreadContext.get().setCurrency("total_concurrency", atomic);
		atomicMethod = ThreadContext.get().getCurrency(methodName);
		atomicMethod.decrementAndGet();
		ThreadContext.get().setCurrency(methodName, atomicMethod);
		
		if (exception != null && ThreadContext.get().getStackInfo().isContain(exception, true)) {
			LoggingUtils.log(Level.ERROR, logger, logContent, exception);
		}

		if (exception != null) {
			throw exception;
		}
		return result;
	}

	private Level getLevel(Method method) {
		return Level.getLevel("INFO");
	}

}
