package com.bjqg.document;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DocumentApplicationTests {


    @Test
    public void contextLoads() {

    }

    @Test
    public void arrayListTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(3);
        list.add(3);
        list.add(-5);
        list.add(7);
        list.add(-9);
        list.add(-7);
        System.out.println("原始数组：" + list);

        //反转
        Collections.reverse(list);
        System.out.println("Collections.reverse(list):" + list);

        //sort，按自然排序的升序排序
        Collections.sort(list);
        System.out.println("Collections.sort(list):" + list);

        //定制排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：" + list);
    }

    @Test
    public void myTest1() {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        HashMap<Object, Object> hashMap = new HashMap<>();
        Collection<Object> values = concurrentHashMap.values();
        System.out.println(values);
    }

    @Test
    public void runnableTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable now is using !");
            }
        }).start();
        new Thread(() -> System.out.println("runnable now is using !")).start();
    }

    @Test
    public void comperatorTest() {
        //内部类
        List<Integer> list = Arrays.asList(1, 2, 3);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        //lambda
        Collections.sort(list, (Integer o1, Integer o2) -> o1 - o2);
        //分解看
        Comparator<Integer> comparator = (Integer o1, Integer o2) -> o1 - o2;
        Collections.sort(list, comparator);
    }

    @Test
    public void streamTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        integerList.stream().forEach(System.out::println);
        ArrayList<String> list = new ArrayList<>();
        list.add("abc0");
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        list.stream().filter(s -> s.endsWith("1")).forEach(System.out::println);
        System.out.println(integerList.stream().distinct().collect(Collectors.toList()));
    }

    @Test
    public void requestControllerTest() {

    }

    @Test
    public void yunshangchanganTest(){
        int init = 1;
        for (int i = 0;i <= 6;i++){
            init = +i;
        }
        System.out.println(init);
    }

    @Test
    public void fastJson2Test(){

    }

}
