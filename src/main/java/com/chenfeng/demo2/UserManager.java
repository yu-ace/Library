package com.chenfeng.demo2;

import com.chenfeng.demo2.model.User;

public class UserManager {

    static User[] users = new User[50];
    static int size;

    public static boolean contain(String username){
        for (User user : users) {
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static User login(String username,String password){
        for (User user : users) {
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public static void signup(String username,String password){
        if(!contain(username)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            users[size] = user;
            size++;
        }
    }
}
