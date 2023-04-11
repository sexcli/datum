package com.bjqg.linkCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 四数之和
 * @author: lbj
 * @date: 2023/4/7 13:46
 */
public class FourSum {

    public static void main(String[] args) {
        List<List<Integer>> list = fourSum(new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,70,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90}, 200);
        System.out.println(list);
    }

//    private static List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> result = IntStream.range(0, nums.length)
//                .boxed()
//                .flatMap(i -> IntStream.range(i + 1, nums.length)
//                        .boxed()
//                        .flatMap(j -> IntStream.range(j + 1, nums.length)
//                                .boxed()
//                                .flatMap(k -> IntStream.range(k + 1, nums.length)
//                                        .mapToObj(l -> Arrays.asList(nums[i], nums[j], nums[k], nums[l])))))
//                .collect(Collectors.toList());
////        List<List<Integer>> res = result.stream().filter(list -> list.stream().mapToInt(Integer::intValue).sum() == target)
////                .map(list -> {
////                    Collections.sort(list);
////                    return new HashSet<>(list);
////                })
////                .collect(Collectors.toSet())
////                .stream().map(ArrayList::new)
////                .collect(Collectors.toList());
//        List<List<Integer>> filteredList = result.stream().filter(list -> list.stream().mapToInt(Integer::intValue).sum() == target)
//                .distinct()
//                .collect(Collectors.toList());
//        Map<String, List<Integer>> resultMap = new LinkedHashMap<>(); // 保留插入顺序的HashMap
//        for (List<Integer> list : filteredList) {
//            List<Integer> sortedList = new ArrayList<>(list); // 复制一份原有list进行排序
//            Collections.sort(sortedList);
//            String key = sortedList.toString(); // 转换为字符串作为key
//            if (!resultMap.containsKey(key)) {
//                resultMap.put(key, list);
//            }
//        }
//        List<List<Integer>> finalResult = new ArrayList<>(resultMap.values());
//        return finalResult;
//
//    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return res;
        }

        Arrays.sort(nums);
        List<List<Integer>> result = IntStream.range(0, nums.length - 3)
                .boxed()
                .flatMap(a -> IntStream.range(a + 1, nums.length - 2)
                        .boxed()
                        .flatMap(b -> IntStream.range(b + 1, nums.length - 1)
                                .boxed()
                                .flatMap(c -> IntStream.range(c + 1, nums.length)
                                        .filter(d -> nums[a] + nums[b] + nums[c] + nums[d] == target)
                                        .mapToObj(d -> Arrays.asList(nums[a], nums[b], nums[c], nums[d]))
                                )
                        )
                )
                .distinct()
                .collect(Collectors.toList());

        return result;

    }




}
