package org.ui.postgresql.adminui.web.requests;

public class Message {
    private String msg;

    public Message(String message){
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
