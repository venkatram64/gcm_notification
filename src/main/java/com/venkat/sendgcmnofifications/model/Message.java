package com.venkat.sendgcmnofifications.model;

public class Message {

    private String msg;

    public Message(){
        this.msg = "";
    }

    public Message(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
