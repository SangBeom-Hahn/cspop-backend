package com.kyonggi.cspop.controller.dto;

public abstract class ValidateMessage {

    // size
    public static final int SIGN_UP_MIN_SIZE = 2;
    public static final int SIGN_UP_MAX_SIZE = 20;
    public static final int NAME_MAX_SIZE = 10;
    public static final int NUMBER_UP_MAX_SIZE = 15;

    // message
    public static final String PASSWORD_MESSAGE = "2 ~ 16자 사이의 비밀번호를 입력해주세요, 영문자, 숫자, 특수문자를 포함해주세요.";
    public static final String EMPTY_MESSAGE = "내용이 비어있습니다, 필수로 입력해 주세요.";
    public static final String NUMBER_SIZE_MESSAGE = "학번은 15글자가 최대입니다.";
    public static final String ID_SIZE_MESSAGE = "아이디는 20글자가 최대입니다.";
    public static final String CLASSIFICATION_SIZE_MESSAGE = "학년은 20글자가 최대입니다.";
    public static final String DEPARTMENT_SIZE_MESSAGE = "학과는 10글자가 최대입니다.";
    public static final String NAME_SIZE_MESSAGE = "이름은 10글자가 최대입니다.";

    // pattern
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{" + 2 + "," + 16 + "}$";
    public static final String EMAIL_PATTERN = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    public static final String PHONE_PATTERN = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
}
