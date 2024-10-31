package cn.dails.logging.context;

import java.lang.reflect.Method;

public class MethodContext {

	private Class<?> controllerClazz;
	private Method method;
	private Object[] args;
	private long     invokedTimestamp;
	private int  	 concurrency;
	private int  	 totalAllow;
	
	
	public MethodContext(){
		invokedTimestamp=System.currentTimeMillis();
	}
	public MethodContext(Class<?> clazz, Method method, Object[] args){
		this.controllerClazz=clazz;
		this.method=method;
		this.args=args;
		invokedTimestamp=System.currentTimeMillis();
	}
	public Class<?> getControllerClazz() {
		return controllerClazz;
	}
	public Method getMethod() {
		return method;
	}
	public Object[] getArgs() {
		return args;
	}
	public long getInvokedTimestamp() {
		return invokedTimestamp;
	}
	public String getTrafficInfo() {
		StringBuilder sb=new StringBuilder(concurrency).append("/").append(totalAllow);
		return sb.toString();
	}
	public int getConcurrency(){
		return concurrency;
	}
	public void setConcurrency(int concurrency) {
		this.concurrency = concurrency;
	}
	public void setTotalAllow(int totalAllow){
		this.totalAllow=totalAllow;
	}
	
}
