package com.radhesham.common;

public class ConstantsPool {

    private ConstantsPool(){
    }
    public static final String ERROR_CODE_SUCCESS="000";
    public static final String ERROR_CODE_INVALID="001";

    public static final String STATUS_SUCCESS="success";
    public static final String STATUS_INVALID="invalid";
    public static final String STATUS_FAIL="fail to execute";
    public static final String STATUS_UNKNOWN="unknown";

    public static final Integer MIN_MOBILE_LENGTH=10;
    public static final Integer MAX_MOBILE_LENGTH=14;

    public static final String NUMBER_PATTERN="^\\d+$";
    public static final String EMAIL_VALIADTION_PATTERN="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

}
