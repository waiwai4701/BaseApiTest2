package com.ww.CollectionTest.ArrayListTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayListTest_answer {
    public static void main(String[] args) {

        //创建固定长度队列
        List<Integer> list =  new ArrayList<>(10);

        //容量大小
        System.out.println("size="+list.size());

        //是否为空
        System.out.println("isEmpty="+list.isEmpty());

        //是否包含某个元素
        list.add(1);
        System.out.println("contains="+list.contains(1));

        //某个元素在list中的首个下标
        list.add(2);
        list.add(3);
        System.out.println("indexOf="+list.indexOf(2));

        //某个元素在list中最大的下标
        list.add(2);
        System.out.println("lastIndexOf="+list.lastIndexOf(2));

       // System.out.println("toString="+ Arrays.toString(list.toArray()));

        //list转array
        Object[] array = (Object[]) list.toArray();
        System.out.println("listToArray="+Arrays.toString(array));

        //得到某个下标的值
        System.out.println("get="+list.get(1));

        //设置某个下标的值
        System.out.println("set="+list.set(1,20));

        //添加
        System.out.println("add="+list.add(9));

        //在某个下标添加
        list.add(4,7);
        System.out.println("addIndex="+list.get(4));

        //删除某个下标
        list.remove(4);
        System.out.println("remove="+list.get(4));

        //清空list
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.clear();
        System.out.println("clear="+list2.size());

        //将一个list添加到另外一个list
        list2.addAll(list);
        System.out.println("addAll="+list2.size());

        //将一个list从另外一个删除
        list2.removeAll(list2);
        System.out.println("removeAll="+list2.size());

        //保留集合中存在的元素
        list2.add(3);
        list2.retainAll(list);
        System.out.println("retainAll="+list2.size());

        //按下标截取生成新的list
        List<Integer> list3 = list.subList(1,5);
        System.out.println("subList="+Arrays.toString(list3.toArray()));

        //排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("sort="+Arrays.toString(list.toArray()));

    }
}
