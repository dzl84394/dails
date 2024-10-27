package cn.dails.base.exceptions;

public class PropertiesWrongException extends AbstractBaseException {

	private static final long serialVersionUID = 1L;
	private static int _CODE=5000;
	private static String msg="error_wrong_properties";
	public PropertiesWrongException(String msg){
		super(_CODE,msg);
	}
}
