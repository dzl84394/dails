package cn.dails.base.exceptions;

public class JavaELFormatException extends AbstractBaseException {

	private static final long serialVersionUID = 1L;
	private static Integer _CODE = 1;
	private static String mge="error_wrong_spel_format";
	public JavaELFormatException(String value){
		super(_CODE,"wrong format with value="+value+",correct SpEL format is like: ${xxx.xxx}");
	}
}
