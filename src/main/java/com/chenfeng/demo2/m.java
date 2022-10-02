package com.chenfeng.demo2;

import com.chenfeng.demo2.model.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class m {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        User s = new User();
        s.setPassword("456");
        s.setUsername("123");

        String sum = "";
        Method[] declaredMethods = s.getClass().getDeclaredMethods();
        for (Method m : declaredMethods) {
            if (m.getName().startsWith("get")){
                String fn = m.getName();
                String pn = fn.substring(3, fn.length());
                String pn1 = pn.substring(0, 1).toLowerCase() + pn.substring(1);
//                System.out.println(pn1);
                String value = m.invoke(s, null).toString();
                sum = sum + (pn1+"="+value+"\t");
            }
        }
        System.out.println(sum);

        User user = new User();
        String[] strings = sum.split("\t");
        for (String s2 : strings) {
            String[] s3 = s2.split("=");
            String pn = s3[0];
            String value = s3[1];
            String fn = "set" + pn.substring(0, 1).toUpperCase() + pn.substring(1);
//            System.out.println(fn);
            for (Method m : declaredMethods) {
                if (m.getName().equals(fn)) {
                    Parameter[] parameters = m.getParameters();
                    Parameter parameter = parameters[0];
                    if(parameter.getType()==int.class){
                        m.invoke(user,Integer.parseInt(value));
                    }else {
                        m.invoke(user, value);
                    }
                }
            }
        }
    }
}
