package com.den.handler;

/**
 * Created by Denis on 28.01.2015.
 */
public abstract class MessageHandler {

    private Boolean success = true;

    protected Object json = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
}
