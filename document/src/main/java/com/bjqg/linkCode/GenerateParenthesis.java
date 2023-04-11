package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过数字n，生成有效的括号。
 * @author: lbj
 * @date: 2023/4/11 14:38
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);
    }

    private static List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        dfs(n,n,"",res);
        return res;

    }

    private static void dfs(int left, int right, String path, ArrayList<String> res) {
        if (left == 0 && right == 0){
            res.add(path);
            return;
        }
        if (left > 0){
            dfs(left-1,right,path+"(",res);
        }
        if (right > left){
            dfs(left,right-1,path+")",res);
        }
    }
}
