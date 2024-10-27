package cn.dails.base.bean;


import cn.dails.base.exceptions.AbstractBaseException;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable{
	
	private static final long serialVersionUID = -5835251989337849261L;
	private Integer resultCode;
	
	private String message;
	

	private T data;

	public BaseResponse ok() {
		this.resultCode = ResultCode.SUCCESS.value();
		this.message = ResultCode.SUCCESS.getResultDesc();
		return this;
	}
	public BaseResponse buildSuccess() {
		return ok();
	}
	public BaseResponse ok(T data) {
		this.resultCode = ResultCode.SUCCESS.value();
		this.message = ResultCode.SUCCESS.getResultDesc();
		this.data = data;
		return this;
	}

	public BaseResponse buildAccepted() {
		this.resultCode = ResultCode.ACCEPTED.value();
		this.message = ResultCode.ACCEPTED.series().toString();
		return this;
	}

	public BaseResponse buildFaild(AbstractBaseException ex) {
		this.resultCode = ex.getCode();
		this.message =ex.getMsg();
		return this;
	}

	public BaseResponse buildFaild() {
		this.resultCode = ResultCode.FAILED.value();
		this.message =ResultCode.FAILED.series().toString();
		return this;
	}

	public BaseResponse buildFaild(Integer errorCode,String resultDesc) {
		this.resultCode = errorCode;
		this.message = resultDesc;
		return this;
	}

	public BaseResponse bindErrorCode(int errorCode) {
		ResultCode resultCode = ResultCode.valueOf(errorCode);
		this.resultCode = errorCode;
		this.message = resultCode.series().toString();
		return this;

	}



	public BaseResponse bindData(T data) {
		this.data =data;
		return this;
	}

	public Integer getResultCode() {
		return resultCode;
	}



	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
