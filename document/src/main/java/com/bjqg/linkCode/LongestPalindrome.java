package com.bjqg.linkCode;

/**
 * 最长回文字串
 * @author: lbj
 * @date: 2023/3/27 9:34
 */
public class LongestPalindrome {
    public static String longestPalindrome(@org.jetbrains.annotations.NotNull String s){
        int length = s.length();
        boolean[][] booleans = new boolean[length][length];
        String longest = "";
        for (int i = length-1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                booleans[i][j] = s.charAt(i) == s.charAt(j) && (j-i < 2 || booleans[i+1][j-1]);
                System.out.println("booleans = "+booleans[i][j]);
                if (booleans[i][j] && j-i+1 > longest.length()){
                    longest = s.substring(i,j+1);
                    System.out.println("longest = "+longest);
                }
            }
        }
        System.out.println(longest);
        return longest;
// 将原字符串转换为奇数长度的字符串
//        StringBuilder sb = new StringBuilder();
//        sb.append("#");
//        for (int i = 0; i < s.length(); i++) {
//            sb.append(s.charAt(i));
//            sb.append("#");
//        }
//        String str = sb.toString();
//
//        // 计算每个字符作为回文中心时的最长回文半径
//        int n = str.length();
//        int[] radius = new int[n];
//        int center = 0, right = 0;
//        for (int i = 0; i < n; i++) {
//            if (i < right) {
//                radius[i] = Math.min(radius[2 * center - i], right - i);
//            } else {
//                radius[i] = 1;
//            }
//            while (i - radius[i] >= 0 && i + radius[i] < n && str.charAt(i - radius[i]) == str.charAt(i + radius[i])) {
//                radius[i]++;
//            }
//            if (i + radius[i] > right) {
//                center = i;
//                right = i + radius[i];
//            }
//        }
//
//        // 找到最长回文子串
//        int maxRadius = 0, maxCenter = 0;
//        for (int i = 0; i < n; i++) {
//            if (radius[i] > maxRadius) {
//                maxRadius = radius[i];
//                maxCenter = i;
//            }
//        }
//        int start = (maxCenter - maxRadius + 1) / 2;
//        int end = (maxCenter + maxRadius - 1) / 2;
//        System.out.println(s.substring(start,end+1));
//        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        String s = "babad";
        longestPalindrome(s);
    }
}
