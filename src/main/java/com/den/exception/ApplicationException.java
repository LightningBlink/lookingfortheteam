package com.den.exception;

/**
 * Created by Denis on 22.05.2015.
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super("Something wrong on server! Operation has been failed!");
    }

    public ApplicationException(String errorText) {
        super(errorText);
    }
}
