package com.bjqg.linkCode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * @author: lbj
 * @date: 2023/4/12 10:24
 */
public class SearchInsert {

    public static void main(String[] args) {
        int searchInsert = searchInsert(new int[]{1, 3, 5, 6}, 3);
        System.out.println(searchInsert);
    }

    private static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0,right = n-1,ans = n;
        while (left <= right){
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]){
                ans = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
