package com.bjqg.web.collection;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: lbj
 * @date: 2022/12/16 10:38
 */
public class MyCollectionTest {

    /**
     * ArrayList的删除喝新增方式
     *
     * @return :
     * @author : lbj
     * @description: <Collections & removeIf方法删除>
     * @date : 2022/12/16 11:05
     */
    public static void myList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        list.removeIf(filter -> filter % 2 == 0);
        System.out.println(list);
    }

    /**
     * set去重示例
     *
     * @return : {@link Set<T>}
     * @param: data
     * @author : lbj
     * @description: <CollectionUtils & isEmpty 方法判断为空 new HashSet>
     * @date : 2022/12/16 11:08
     */
    public static <T> Set<T> removeDuplicateBySet(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new HashSet<>();
        }
        return new HashSet<>(data);
    }

    /**
     * List 去重方法示例
     *
     * @return : {@link List<T>}
     * @param: data
     * @author : lbj
     * @description: <CollectionUtils & isEmpty 判断为空 new ArrayList>
     * @date : 2022/12/16 11:13
     */
    public static <T> List<T> removeDuplicateByList(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        ArrayList<T> result = new ArrayList<>(data.size());
        for (T current : data) {
            if (!result.contains(current)) {
                result.add(current);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            data.add(i);
        }

    }
}
