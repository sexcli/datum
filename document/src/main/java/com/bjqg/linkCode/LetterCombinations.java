package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 电话号码的字母组合
 * @author: lbj
 * @date: 2023/4/6 14:45
 */
public class LetterCombinations {

    public static void main(String[] args) {
        List<String> list = letterCombinations("2345");
        System.out.println(list);
    }

    private static List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        ArrayList<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return combinations;
        }

        char[][] letters = new char[digits.length()][];
        for (int i = 0; i < digits.length(); i++) {
            letters[i] = map.get(digits.charAt(i));
        }

        combine(letters, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private static void combine(char[][] letters, int index, StringBuilder sb, List<String> combinations) {
        if (index == letters.length) {
            combinations.add(sb.toString());
            return;
        }
        for (int i = 0; i < letters[index].length; i++) {
            sb.append(letters[index][i]);
            combine(letters, index + 1, sb, combinations);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
