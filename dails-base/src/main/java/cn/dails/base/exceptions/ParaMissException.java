package cn.dails.base.exceptions;

public class ParaMissException extends AbstractBaseException {

	private static final long serialVersionUID = 1L;
	private static int _CODE=5000;
	private static String msg="error_missing_parameter";
	public ParaMissException(String msg){
		super(_CODE,msg);
	}
}
