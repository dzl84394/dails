package cn.dails.base.exceptions;


public abstract class AbstractBaseException extends Exception {

	private static final long serialVersionUID = -1056116650960507247L;

	public Integer code;
	public String msg;
	
	public AbstractBaseException(Integer code, String msg) {
		super(new StringBuilder()
				.append(",[code]=").append(code)
				.append(",[msg]=").append(msg).toString());
		this.code = code;
		this.msg = msg;

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
