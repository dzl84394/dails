package cn.dails.base.exceptions;

public class NoSuchClientInfoException extends AbstractBaseException {
	private static final long serialVersionUID = 1L;
	private static int _CODE=5000;
	private static String msg="error_no_such_client_info";
	public NoSuchClientInfoException(String msg){
		super(_CODE,msg);
	}
}
