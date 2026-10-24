package com.den.handler;

/**
 * Created by Denis on 28.01.2015.
 */
public class SuccessMessageHandler  extends MessageHandler{

    private String message;

    public SuccessMessageHandler(){
        this.setSuccess(true);
    }

    public SuccessMessageHandler(String message) {
        this.setSuccess(true);
        this.message = message;
    }

    public SuccessMessageHandler(String message, Object json) {
        this.setSuccess(true);
        this.message = message;
        this.json = json;
    }




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
