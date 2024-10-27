package cn.dails.base.exceptions;


import cn.dails.base.bean.ResultCode;

public class MyException extends Exception {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3428821882963103704L;
	
	
	private String msg = "fm_timeout_exception";
    private ResultCode resultCode;
    private String resultDesc;
    private String errorCode;

    public MyException(String msg, ResultCode resultCode, String resultDesc, String errorCode){
        super(msg);
        this.resultCode = resultCode;
        this.errorCode=errorCode;
        this.resultDesc = resultDesc;
    }

    public MyException(String msg){
        super(msg);
        this.resultCode = ResultCode.FAILED;

        this.resultDesc = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
