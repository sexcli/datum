package com.bjqg.linkCode;

import java.util.HashMap;

/**
 * 罗马数字转整数
 * @author: lbj
 * @date: 2023/4/3 9:33
 */
public class RomanToInt {

    public static void main(String[] args) {
        int iv = romanToInt("IV");
        System.out.println(iv);
    }

    private static int romanToInt(String s) {
        HashMap<Character, Integer> symbolValues = new HashMap<Character, Integer>(){{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            Integer value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i+1))){
                ans -= value;
            }else {
                ans += value;
            }
        }
        return ans;
    }


}
