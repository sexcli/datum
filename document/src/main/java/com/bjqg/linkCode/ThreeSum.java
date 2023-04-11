package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @author: lbj
 * @date: 2023/4/3 17:06
 */
public class ThreeSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // 先将数组排序
        for (int i = 0; i < nums.length -2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;  // 避免重复计算
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++; right--;
                    while (left < right && nums[left] == nums[left-1]) left++;  // 避免重复计算
                    while (left < right && nums[right] == nums[right+1]) right--;  // 避免重复计算
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
