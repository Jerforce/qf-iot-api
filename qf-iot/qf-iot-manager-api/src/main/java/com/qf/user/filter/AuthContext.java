package com.qf.user.filter;


import com.qf.user.entity.User;

public class AuthContext {

   private static final ThreadLocal<User> context = new ThreadLocal();


    public static void setUser(User user){
        context.set(user);
    }

    public static User getUser(){
        return  context.get();
    }

    public static void clear(){
        context.remove();
    }
}