package cn.dails.base.exceptions;

public class UserTokenIllegalException extends AbstractBaseException {
	private static final long serialVersionUID = 1L;
	private static int _CODE=5000;
	private static String msg="error_user_token_illegal";

	public UserTokenIllegalException(String msg){
		super(_CODE,msg);
	}
}
