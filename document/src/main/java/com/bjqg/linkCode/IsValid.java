package com.bjqg.linkCode;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * @author: lbj
 * @date: 2023/4/10 16:28
 */
public class IsValid {

    public static boolean isValid(String s){
        int n = s.length();
        if (n % 2 == 1){
            return false;
        }
        HashMap<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        System.out.println(map);
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                if (stack.isEmpty() || stack.peek() != map.get(c)){
                    return false;
                }
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        boolean b = isValid("()[]{}");
        System.out.println(b);
    }
}
