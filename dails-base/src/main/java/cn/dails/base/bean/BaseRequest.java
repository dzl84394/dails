package cn.dails.base.bean;

import java.io.Serializable;

public class BaseRequest<T> implements Serializable{

	private static final long serialVersionUID = -4485956924137942441L;



	private T data;

    public BaseRequest<T> bindBody(T t) {
    	this.data = t;
    	return this;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
