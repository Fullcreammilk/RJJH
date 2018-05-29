package com.edu.nju.wel.exception;

/**
 * Created by qianzhihao on 2017/1/17.
 */
public class UserPwdCanNotBeNullException extends Exception {

    private static final long serialVersionUID = -468750579509136282L;

    public UserPwdCanNotBeNullException(String message) {
        super(message);
    }
}
