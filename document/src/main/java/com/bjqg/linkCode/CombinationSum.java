package com.bjqg.linkCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * @author: lbj
 * @date: 2023/4/21 15:00
 */
public class CombinationSum {

    public static void main(String[] args) {
        int target = 8;
        int[] candidates = {10,1,2,7,6,1,5};
        List<List<Integer>> list = combinationSum(candidates, target);
        System.out.println(list);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates,target,0,new ArrayList<>(),res);
        return res;
    }

    private static void backtrack(int[] candidates, int target, int start, ArrayList<Integer> combination, ArrayList<List<Integer>> res) {
        if (target == 0){
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (target - num < 0){
                break;
            }
            combination.add(num);
            backtrack(candidates,target - num, i, combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}
