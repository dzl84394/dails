package cn.dails.logging.context;

import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.UUID;


public class StackInfo {

	private static final String _AUTO_GEN_TRC_PREFIX = "trc_";

	
	protected String traceId;
	//private BasicRestResponse restResponse; 
	protected ServiceCentext fromClientInfo;
	protected ServiceCentext localClientInfo;

	private MethodContext ctlMethodCtx;
	
	
	private HashMap<Integer, Throwable> exceptionsOccour;
	
	
	public StackInfo() {
		
	}
	
	
	
	public void clear(){
		if(exceptionsOccour!=null) exceptionsOccour.clear();
	}
	
	/**
	 * 在本次调用stack中是否已经记录了该异常,没有没有则记录
	 * @param e
	 * @return
	 */
	public boolean isContain(Throwable e,boolean autoSave){
		if(e==null) return false;
		if (exceptionsOccour==null) exceptionsOccour=new HashMap<>();
		Integer id=System.identityHashCode(e);
		if (exceptionsOccour.containsKey(id)){
			return true;
		}
		if(autoSave){
			exceptionsOccour.put(id, e);
		}
		return false;
	}
	
	public String getTraceId() {
		if (traceId!=null) return traceId;
		this.traceId = _AUTO_GEN_TRC_PREFIX + UUID.randomUUID().toString();
		return traceId;
	}
	

	public void setTraceId(String traceId) {
		if (Strings.isNullOrEmpty(traceId))
			traceId = _AUTO_GEN_TRC_PREFIX + UUID.randomUUID().toString();
		this.traceId = traceId;
	}


	
	public void setCtlMethodCtx(MethodContext ctlMethodCtx){
		this.ctlMethodCtx=ctlMethodCtx;
	}
	public MethodContext getCtlMethodCtx(){
		return this.ctlMethodCtx;
	}

	

	
	
	/**
	 * 获得根部调用者的ip地址
	 * @return
	 */
	public String getFromClientIp(){
		if(fromClientInfo==null) return null;
		return fromClientInfo.getClientIp();
	}
	/**
	 * 获得调用者的ip地址
	 * @return
	 */
	public String getLocalClientIp(){
		if(localClientInfo==null) return null;
		return localClientInfo.getClientIp();
	}



	public ServiceCentext getFromClientInfo() {
		return fromClientInfo;
	}



	public void setFromClientInfo(ServiceCentext fromClientInfo) {
		this.fromClientInfo = fromClientInfo;
	}



	public ServiceCentext getLocalClientInfo() {
		return localClientInfo;
	}



	public void setLocalClientInfo(ServiceCentext localClientInfo) {
		this.localClientInfo = localClientInfo;
	}

	
	
	
}
