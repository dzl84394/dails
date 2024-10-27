package cn.dails.base.exceptions;

public class CellphoneFormatException extends AbstractBaseException {

	private static final long serialVersionUID = 1L;
	private static int _CODE=5000;
	private static String msg="error_cellphone_format：";
	public CellphoneFormatException(String cellphone){
		super(_CODE,msg+cellphone);
	}


}
