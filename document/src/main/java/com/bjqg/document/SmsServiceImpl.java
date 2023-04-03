package com.bjqg.document;

/**
 * @author: lbj
 * @date: 2023/3/23 17:29
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
