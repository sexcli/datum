package com.bjqg.linkCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 寻找字符串最长公共前缀
 * @author: lbj
 * @date: 2023/4/3 14:23
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String s = longestCommonPrefix(new String[]{"flows", "flow", "flower"});
        System.out.println(s);
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 0; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
}
