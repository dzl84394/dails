package cn.dails.logging.context;

import cn.dails.logging.utils.CommonTools;

import java.lang.reflect.Method;
import java.util.List;


public class LogContent extends BasicLogContent {
	
	public static final String _NULL_OUTPUT="_NULL_RETURN";
	
//	在jackson中  @JSONField(ordinal = 13)ordinal 数字越小，先被序列化 ，format是序列化是自动转化为设定的格式
//	@JSONField(ordinal = 10)
	
	protected Long costTimeMillis;
	protected String method;
	protected List<Object> inputs;
	protected Object output;
	
	protected Object concurrency;
	protected Object totalConcurrency;
	
	
	protected Throwable throwable;
	
	
	
	public LogContent(String method, List<Object> inputs, Object output, long costTimeMillis, Integer concurrency, Integer totalConcurrency, Throwable throwable){
		super();
		this.method= method;
//		this.method=CommonTools.getMethodDeclareInfo(method);
		this.costTimeMillis=costTimeMillis;
		this.inputs=inputs;



		this.output=output;


		this.throwable=throwable;
		this.concurrency=concurrency;
		this.totalConcurrency=totalConcurrency;
		
		
	}
	public LogContent(Method method, List<Object> inputs, Object output, long costTimeMillis, Throwable throwable){
		super();
		this.method= method.getDeclaringClass().getName()+"."+method.getName();
//		this.method=CommonTools.getMethodDeclareInfo(method);
		this.costTimeMillis=costTimeMillis;
		this.inputs=inputs;
		this.output=output;
		this.throwable=throwable;
	}
	

	public Long getCostTimeMillis() {
		return costTimeMillis;
	}

	public String getMethod() {
		return method;
	}

	public List<Object> getInputs() {
		return inputs;
	}

	public Object getOutput() {
		if (output==null) return _NULL_OUTPUT;
		return output;
	}
	public String getExceptionInfo(){
		if (throwable==null){
			return null;
		}
		return CommonTools.getExceptionSimpleInfo(throwable);
	}

	public Object getConcurrency() {
		return concurrency;
	}

	public Object getTotalConcurrency() {
		return totalConcurrency;
	}


	public void setCostTimeMillis(Long costTimeMillis) {
		this.costTimeMillis = costTimeMillis;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setInputs(List<Object> inputs) {
		this.inputs = inputs;
	}

	public void setOutput(Object output) {
		this.output = output;
	}

	public void setConcurrency(Object concurrency) {
		this.concurrency = concurrency;
	}

	public void setTotalConcurrency(Object totalConcurrency) {
		this.totalConcurrency = totalConcurrency;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
