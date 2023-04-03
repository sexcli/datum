package com.bjqg.document;

import com.google.common.collect.LinkedHashMultimap;

import java.util.*;

/**
 * @author: lbj
 * @date: 2023/3/22 13:02
 */
public class ArraysTest {
//    public static void main(String[] args) {
//        final String str =  "C1bZc5A3wBa";
//        String integerStr = "";
//        String s = "";
//        String s1 = "";
//        String s2 = "";
//        System.out.println(str);
//        char[] chars = str.toCharArray();
//        for (char aChar : chars) {
//            if (aChar >= 48 && aChar <= 57){
//                integerStr = integerStr + aChar;
//                continue;
//            }else if ((aChar >= 97 && aChar <= 122) || (aChar >= 65 && aChar <= 90)) { //97-122小写字母  65-90大写字母
//                s = s + aChar;
//            }
//        }
//        char[] charArray = s.toCharArray();
//        for (char c : charArray) {
//            if ((c >= 97 && c <= 122)){
//                s1 = s1 + c;
//            }else if ((c >= 65 && c <= 90)){
//                s2 = s2 + c;
//            }
//        }
//        System.out.println(integerStr);
//        System.out.println(s);
//        System.out.println(s1);
//        System.out.println(s2);
//    }

    public static void main(String[] args) {
        String input = "HelloWorld";
        String output = replaceAndSort(input);
        System.out.println(output);
    }

    public static String replaceAndSort(String input) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('B', 3);
        map.put('C', 5);
        map.put('D', 7);
        map.put('E', 9);
        map.put('F', 11);
        map.put('G', 13);
        map.put('H', 15);
        map.put('I', 17);
        map.put('J', 19);
        map.put('K', 21);
        map.put('L', 23);
        map.put('M', 25);
        map.put('N', 26);
        map.put('O', 24);
        map.put('P', 22);
        map.put('Q', 20);
        map.put('R', 18);
        map.put('S', 16);
        map.put('T', 14);
        map.put('U', 12);
        map.put('V', 10);
        map.put('W', 8);
        map.put('X', 6);
        map.put('Y', 4);
        map.put('Z', 2);

        char[] chars = input.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                int value = map.get(c);
                list.add((char)(value + '0'));
            } else {
                list.add(c);
            }
        }
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if (Character.isLowerCase(o1) && Character.isUpperCase(o2)) {
                    return -1;
                } else if (Character.isUpperCase(o1) && Character.isLowerCase(o2)) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}
