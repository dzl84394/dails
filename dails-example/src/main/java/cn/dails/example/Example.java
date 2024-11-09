package cn.dails.example;

public abstract class Example {
	private String serviceName;
	private String rooturl;

	private String url;
	private String workUrl;

	private String resourceUrl;
	private String jsUrl;
	private Class<?> entityClass;
	
	void initFiles(){
		
	}
	void initJava(){
		
	}
	void initJsp(){
		
	}
	
	
	
	
	
	
	
	/**---------------------------------------***/
	public String getRooturl() {
		return rooturl;
	}
	public void setRooturl(String rooturl) {
		this.rooturl = rooturl;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName =  serviceName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Class<?> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	public String getWorkUrl() {
		return getRooturl() + "/src/main/java/"+ getUrl();
	}
	public void setWorkUrl(String workUrl) {
		this.workUrl = workUrl;
	}


	public String getResourceUrl() {
		return getRooturl() + "/src/main/resources/";
	}

	public String getJsUrl() {
		return getRooturl() + "/src/main/resources"+jsUrl;
	}

	public void setJsUrl(String jsUrl) {
		this.jsUrl = jsUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
}
