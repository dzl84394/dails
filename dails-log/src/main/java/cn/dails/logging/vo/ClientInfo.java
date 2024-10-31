package cn.dails.logging.vo;


import com.google.gson.annotations.SerializedName;

public class ClientInfo {

	private String clientId;

//	@JSONField(name = "clientIP")
	@SerializedName("clientIp")
	private String clientIp;

//	@JSONField(name = "clientUA")
	@SerializedName("clientUA")
	private String ua;

	private String appName;
	private String moduleName;

	
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}



	
}
