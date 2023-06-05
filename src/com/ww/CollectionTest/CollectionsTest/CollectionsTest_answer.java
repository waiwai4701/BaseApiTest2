package com.ww.CollectionTest.CollectionsTest;

import java.util.*;

public class CollectionsTest_answer {

    public static void main(String[] args) {

        //生成不可修改的集合
        List<String> list = new ArrayList<>();
        list.add("1");
        List<String> unmodifiedList = Collections.unmodifiableList(list);
        //unmodifiedList.add("2");//此处应该报错

        //生成线程安全集合
        List<String> synList = Collections.synchronizedList(list);

        //生成空的集合
        List<String> empList = Collections.emptyList();
        System.out.println("empList.size="+empList.size());

        //排序
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);list2.add(1);
        Collections.sort(list2);
        System.out.println("sort="+Arrays.toString(list2.toArray()));

        //反向排序
        Collections.reverse(list2);
        System.out.println("reverse="+Arrays.toString(list2.toArray()));

        //对比较器操作
        TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder(new StrLenComparator()));
        ts.add("abcde");
        ts.add("aaa");
        ts.add("kkk");
        ts.add("ccc");

        for (String s : ts) {
            System.out.println(s);
        }

        //得到某个集合在另外一个集合中的index
        List<String> arrlistsrc = new ArrayList<String>();
        List<String> arrlisttarget = new ArrayList<String>();

        // Adding element to arrlistsrc
        arrlistsrc.add("A");
        arrlistsrc.add("B");
        arrlistsrc.add("C");
        arrlistsrc.add("D");
        arrlistsrc.add("E");
        arrlistsrc.add("C");
        arrlistsrc.add("D");
        arrlistsrc.add("E");

        // Adding element to arrlisttarget
        arrlisttarget.add("C");
        arrlisttarget.add("D");
        arrlisttarget.add("E");

        // print the source list
        System.out.println("Source list: " + arrlistsrc);

        // print the target list
        System.out.println("Target list: " + arrlisttarget);

        // check target list in source list
        int index = Collections
                .indexOfSubList(arrlistsrc,
                        arrlisttarget);

        // print the index
        System.out.println("Target list starts at index: "
                + index);
        System.out.println("lastIndexOf="+Collections.lastIndexOfSubList(arrlistsrc,arrlisttarget));

        //对集合进行重新打乱
        Collections.shuffle(list2);
        System.out.println("shuffile="+Arrays.toString(list2.toArray()));

        //交换某两个下标的值
        Collections.sort(list2);
        Collections.swap(list2,0,1);
        System.out.println("swap="+Arrays.toString(list2.toArray()));

        //替换某个值的全部value为新的值
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);list3.add(2);list3.add(3);
        Collections.replaceAll(list3,2,22);
        System.out.println("replaceAll="+Arrays.toString(list3.toArray()));

        //把一个集合的内容复制到另外一个集合的结尾,数量不相等不能copy
        //List<Integer> list5 = new ArrayList<>(list2.size());
        //Collections.copy(list5 , list2);
        //System.out.println("copy="+Arrays.toString(list5.toArray()));

        //返回集合最大值和最小值，可以带比较方法
        System.out.println("min="+Collections.min(list2));
        System.out.println("max="+Collections.max(list2));

        //全部替换成某个值
        List<Integer> list4 = new ArrayList<>();
        list4.add(1);list4.add(2);list4.add(3);
        Collections.fill(list4,444);
        System.out.println("replaceAll="+Arrays.toString(list4.toArray()));

        //返回某个值的频率
        System.out.println("frequency="+Collections.frequency(arrlistsrc,"D"));

    }

}



class StrLenComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return 1;
        }
        if (s1.length() < s2.length()) {
            return -1;
        }
        return s1.compareTo(s2);
    }

}
