package cn.dails.base.exceptions;

public class JavaELFormatException extends AbstractBaseException {

	private static final long serialVersionUID = 1L;
	private static String _CODE="error_wrong_spel_format";
	
	public JavaELFormatException(String value){
		super(_CODE,"wrong format with value="+value+",correct SpEL format is like: ${xxx.xxx}");
	}
}
