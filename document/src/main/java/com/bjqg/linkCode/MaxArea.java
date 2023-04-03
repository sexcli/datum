package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 盛取最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * @author: lbj
 * @date: 2023/3/29 10:19
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int i = maxArea(height);
        System.out.println(i);
    }

    public static int maxArea(int[] height){
//        int n = height.length;
//        System.out.println("n :" + n);
//        int x = 0;
//        int y = 0;
//        ArrayList<Integer> integers = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            x = height[i];
//            System.out.println("i的值为"+i+"且x="+x);
//            for (int j = n-1; j >= i; j--) {
//                y = height[j];
//                System.out.println("j的值为"+j+"且y="+y);
//                if (i <= j){
//                    if (x > y){
//                        integers.add(y * (j-i));
//                    }else {
//                        integers.add(x * (j-i));
//                    }
//                }else {
//                    break;
//                }
//            }
//        }
//        Integer max = Collections.max(integers);
//        return max;
        int l = 0,r = height.length-1;
        int ans = 0;
        while (l < r){
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]){
                ++l;
            }else {
                --r;
            }
        }
        return ans;
    }
}
