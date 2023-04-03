package com.bjqg.linkCode;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * 寻找两个正序数组的中位数
 * @author: lbj
 * @date: 2023/3/24 13:39
 */
public class FindMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int[] nums = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();

        long count = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).count();
        OptionalDouble average;
        if (count%2 == 0){
            average = Arrays.stream(nums).skip(count / 2 - 1).limit(2).average();
        }else {
            average = Arrays.stream(nums).skip(count / 2).limit(1).average();
        }
        System.out.println(String.format("%.5f", average.getAsDouble()));
        return average.getAsDouble();

//        Arrays.sort(nums1);
//        Arrays.sort(nums2);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,5,7,9};
        int[] nums2 = {2,4,6,8,10,1,3,5};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
