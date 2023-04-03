package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 字符串转换整数
 * @author: lbj
 * @date: 2023/3/28 13:42
 */
public class MyAtoi {
    public static int myAtoi(String s) {
//        ArrayList<Byte> arrayList = new ArrayList<>();
//        byte[] sBytes = s.getBytes();
//        for (int i = 0; i < sBytes.length; i++) {
//            while (sBytes[i] >= 48 && sBytes[i] <= 57){
//                arrayList.add(sBytes[i]);
//                break;
//            }
//        }
//        System.out.println(arrayList);
//        return 0;
        Automata automata = new Automata();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            automata.get(aChar);
        }
        System.out.println(automata.sign * (int) automata.ans);
        return automata.sign * (int) automata.ans;
    }

    static class Automata{
        private int state = 0;
        private int[][] table = {{0,1,2,3},{3,3,2,3},{3,3,2,3},{3,3,3,3,}};
        long ans = 0;
        int sign = 1;

        public int gets(char c){
            if (c==' '){
                return 0;
            }else if (c=='+' || c=='-'){
                return 1;
            }else if (Character.isDigit(c)){
                return 2;
            }else {
                return 3;
            }
        }

        public void get(char c){
            state = table[state][gets(c)];
            if (state == 2){
                ans = ans * 10 + (c-'0');
                ans = sign == 1 ? Math.min(ans,Integer.MAX_VALUE):Math.min(ans,-(long)Integer.MIN_VALUE);
            }if (state == 1 && c == '-'){
                sign = -1;
            }
        }
    }



    public static void main(String[] args) {
        myAtoi("   123456  abc  ");
    }
}
