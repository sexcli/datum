package com.bjqg.linkCode;

/**
 * 整数反转
 * @author: lbj
 * @date: 2023/3/27 16:58
 */
public class Reverse {
    public static int reverse(int x){
        long n = 0;
        while (x != 0){
            n = n*10 + x%10;
            x = x/10;
        }
        System.out.println((int)n == n ? (int)n:0);
        return (int)n == n ? (int)n:0;
    }

    public static void main(String[] args) {
        reverse(-2147483648);
    }
}
