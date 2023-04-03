package com.bjqg.linkCode;

/**
 * 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * @author: lbj
 * @date: 2023/3/30 9:30
 */
public class IntToRoman {
    final static int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    final static String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public static String intToRoman(int num){
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value){
                num -= value;
                roman.append(symbol);
            }
            if (num == 0){
                break;
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        String s = intToRoman(888);
        System.out.println(s);
    }
}
