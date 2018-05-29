package com.edu.nju.wel.exception;

/**
 * Created by qianzhihao on 2017/1/17.
 */
public class UserAlreadyExistException extends Exception {
    private static final long serialVersionUID = 3196649495984787566L;

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
