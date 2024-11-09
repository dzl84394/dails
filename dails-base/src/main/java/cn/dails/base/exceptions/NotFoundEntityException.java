package cn.dails.base.exceptions;

public class NotFoundEntityException extends AbstractBaseException {

	private static final long serialVersionUID = 1L;
	private static int _CODE=5001;
	private static String msg="error_not_found_entity";
	public NotFoundEntityException(String msg){
		super(_CODE,msg);
	}
}
