package com.den.handler;

/**
 * Created by Denis on 28.01.2015.
 */
public class ErrorMessageHandler extends MessageHandler {


    private String message;

    public ErrorMessageHandler(){
        this.setSuccess(false);
    }

    public ErrorMessageHandler(String message) {
        this.setSuccess(false);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
