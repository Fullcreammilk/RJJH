package com.edu.nju.wel.exception;

/**
 * Created by qianzhihao on 2017/1/17.
 */
public class UserCanNotBeNullException extends Exception {

    private static final long serialVersionUID = -4048305269367672326L;

    public UserCanNotBeNullException(String message) {
        super(message);
    }
}
