package com.ww.CollectionTest.HashMapTest;

import java.util.*;

public class HashMapTest_answer {
    public static void main(String[] args) {
        //创建固定长度HashMap
        Map<Integer, String> map = new HashMap<>(10);

        //以一个HashMap为基础创建另外一个HashMap
        Map<Integer, String> map2 = new HashMap<>(map);

        //长度
        System.out.println("size="+map.size());

        //是否为空
        System.out.println("isEmpty="+ map.isEmpty());

        //放入键值对
        map.put(1,"a");

        //获取key对应的value
        System.out.println("get="+map.get(1));

        //判断是否包含某个key
        System.out.println("containsKey="+map.containsKey(1));
        System.out.println("containsValue="+map.containsValue("a"));


        //将一个HashMap放入另外一个
        map2.putAll(map);
        System.out.println("putAll="+map2.size());

        //删除某个key对应的map
        map.remove(1);
        System.out.println("remove="+map.size());

        //清空
        map.clear();
        System.out.println("clear="+map.size());

        //获取所有的keyset
        map.put(1,"a");
        map.put(2,"b");
        Set<Integer> keySet = map.keySet();
        System.out.println("keySet="+ Arrays.toString(keySet.toArray()));

        //获取所有的值
        Collection<String> values = map.values();
        System.out.println("values="+Arrays.toString(values.toArray()));

        //getOrDefault方法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。
        System.out.println("getOrDefault="+map.getOrDefault(1,"aaa"));
        System.out.println("getOrDefault2="+map.getOrDefault(5,"bb"));

        //putIfAbsent 方法会先判断指定的键（key）是否存在，不存在则将键/值对插入到 HashMap 中。
        //参考：https://www.runoob.com/java/java-hashmap-getordefault.html
        map.putIfAbsent(1,"1a1a");
        System.out.println("putIfAbsent="+map.get(1));

        map.putIfAbsent(6,"6a6a");
        System.out.println("putIfAbsent="+map.get(6));

        //computeIfAbsent方法对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。
        map.put(3,"c");
        map.computeIfAbsent(3,key->"ccc");
        System.out.println("putIfAbsent="+map.get(3));

        map.computeIfAbsent(4,key->"ddd");
        System.out.println("putIfAbsent2="+map.get(4));

        //computeIfPresent 方法对 hashMap 中指定 key 的值进行重新计算，前提是该 key 存在于 hashMap 中
        map.put(7,"e");
        map.computeIfPresent(7,(key,value)->key + value);
        System.out.println("computeIfPresent="+map.get(7));

        //compute 法对 hashMap 中指定 key 的值进行重新计算。
        map.computeIfPresent(8,(key,value)->key+value);
        System.out.println("compute="+map.get(8));

        //forEach
        System.out.print("forEach=");
        map.forEach((key,value)->System.out.println("key="+key+",value="+value));

    }
}
