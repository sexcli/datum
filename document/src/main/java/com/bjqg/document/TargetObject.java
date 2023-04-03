package com.bjqg.document;

/**
 * @author: lbj
 * @date: 2023/3/21 15:51
 */
public class TargetObject {

    private String value;

    public TargetObject(){
        value = "LQG";
    }

    public void publicMethod(String s){
        System.out.println("I love " + s);
    }

    private void privateMethod(){
        System.out.println("value is "+ value);
    }
}
