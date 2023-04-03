package com.bjqg.linkCode;

/**
 * 判断是否为回文数
 * @author: lbj
 * @date: 2023/3/28 15:53
 */
public class IsPalindrome {

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int n = s.length();
        boolean[][] booleans = new boolean[n][n];
        boolean bl = false;
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                booleans[i][j] = s.charAt(i) == s.charAt(j) && (j-i < 2 || booleans[i+1][j-1]);
                bl = booleans[i][j];
            }
        }
        System.out.println(bl);
        return bl;
    }

    public static void main(String[] args) {
        isPalindrome(-3454);
    }
}
