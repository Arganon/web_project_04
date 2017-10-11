package com.wb4.utilites;

public class ValidateInputData {
    public final static String TEXT = "^[^<>().*]{3,20}$";
    public final static String PASSWORD = "^[^<>().*]{3,20}$";
    public static final String EMAIL = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,7}))+$";
    public static final String PHONE = "([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})";

    public static boolean checkData(String data, String regex) {
	    	if (data == null || !data.matches(regex)) {
	    		return false;
	    	}
    		return true;
    }
}
