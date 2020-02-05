package com.aaif_seriex.flo.model;

public class User {
    public String name;
    public String email;
    public String avata;
    public Message message;


    public User(){
        message = new Message();
        message.setChatBotName(" ");
    }
}
