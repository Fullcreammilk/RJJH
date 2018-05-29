package com.edu.nju.wel.exception;

/**
 * Created by qianzhihao on 2017/1/17.
 */
public class UserNameCanNotBeNullException extends Exception {

    private static final long serialVersionUID = 6609536387621705667L;

    public UserNameCanNotBeNullException(String message) {
        super(message);
    }
}
