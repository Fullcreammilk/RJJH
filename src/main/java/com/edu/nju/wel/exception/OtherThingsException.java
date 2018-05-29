package com.edu.nju.wel.exception;

/**
 * Created by qianzhihao on 2017/1/17.
 */
public class OtherThingsException extends Exception {

    private static final long serialVersionUID = -3112905715536603426L;

    public OtherThingsException(Exception e) {
        super(e);
    }

    public OtherThingsException(String message) {
        super(message);
    }
}
