package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列
 * @author: lbj
 * @date: 2023/3/27 15:59
 */
public class Convert {
    public static String convert(String s,int numRows){
        if(numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) {
            res.append(row);
        }
        System.out.println(res.toString());
        return res.toString();
    }

    public static void main(String[] args) {
        convert("PAYPALISHIRING",3);
    }
}
