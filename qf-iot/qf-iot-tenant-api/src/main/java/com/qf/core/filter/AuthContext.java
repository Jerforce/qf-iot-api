package com.qf.core.filter;


import com.qf.login.entity.Tenant;

public class AuthContext {

   private static final ThreadLocal<Tenant> context = new ThreadLocal();


    public static void setUser(Tenant tenant){
        context.set(tenant);
    }

    public static Tenant getUser(){
        return  context.get();
    }

    public static void clear(){
        context.remove();
    }
}