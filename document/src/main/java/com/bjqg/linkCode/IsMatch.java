package com.bjqg.linkCode;

/**
 * 正则表达式匹配
 * @author: lbj
 * @date: 2023/3/28 16:41
 */
public class IsMatch {

    public static void main(String[] args) {
        boolean res = isMatch("aaa", "a*");
        System.out.println(res);
    }

    private static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*'){
                    f[i][j] = f[i][j-2];
                    if (matches(s,p,i,j-1)){
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                }else {
                    if (matches(s,p,i,j)){
                        f[i][j] = f[i-1][j-1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private static boolean matches(String s, String p, int i, int j) {
        if (i==0){
            return false;
        }
        if (p.charAt(j-1) == '.'){
            return true;
        }
        return s.charAt(i-1) == p.charAt(j-1);
    }
}
