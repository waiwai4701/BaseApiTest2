package com.ww.CollectionTest.StreamTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 参考：https://www.jianshu.com/p/2eeb8d97d8c6
 *
 *
 */
public class StreamTest_answer {

    public static void main(String[] args) {

        //-----stream的创建----
        //list创建顺序流和并行流
        List<String> listn = Arrays.asList("a", "b", "c");
        // 创建一个顺序流
        Stream<String> stream = listn.stream();
        // 创建一个并行流
        Stream<String> parallelStream = listn.parallelStream();

        //array创建流
        int[] array={1,3,5,6,8};
        IntStream stream2 = Arrays.stream(array);


        //静态创建stream  of() iterate() generate()
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream4.forEach(System.out::print); // 0 3 6 9
        System.out.println();

        Stream<Double> stream5 = Stream.generate(Math::random).limit(3);
        stream5.forEach(System.out::print);
        System.out.println();

        //-----Stream-----
        //遍历/匹配（foreach/find/match）
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::print);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);


        //筛选（filter）
        List<Integer> list2 = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        Stream<Integer> stream6 = list2.stream();
        stream6.filter(x -> x > 7).forEach(System.out::println);

        //聚合（max/min/count)
        List<String> list3 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list3.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
        //获取Integer集合中的最大值。
        List<Integer> list4 = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max2 = list4.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max3 = list4.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("自然排序的最大值：" + max2.get());
        System.out.println("自定义排序的最大值：" + max3.get());

        //计算Integer集合中大于6的元素的个数。
        List<Integer> list5 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list5.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数：" + count);

        //映射(map/flatMap)
        //map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        //flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        //英文字符串数组的元素全部改为大写。整数数组每个元素+3
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写：" + strList);
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素+3：" + intListNew);
        //将两个字符数组合并成一个新的字符数组。
        List<String> list6 = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list6.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合：" + list6);
        System.out.println("处理后的集合：" + listNew);

        //归约(reduce) 是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
        //Lambda反复结合每个元素，直到流被归约成一个值
        List<Integer> list7 = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list7.stream().reduce(Integer::sum);
        // 求和方式2
        Optional<Integer> sum2 = list7.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list7.stream().reduce(0, Integer::sum);
        // 求乘积
        Optional<Integer> product = list7.stream().reduce((x, y) -> x * y);
        // 求最大值方式1
        Optional<Integer> max4 = list7.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max5 = list7.stream().reduce(1, Integer::max);
        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
        //连接字符串
        List<String> listStr = Arrays.asList("a","b","c","d","e","f");
        String reduce = listStr.stream().reduce("",(a,b)->{
            if(!"".equals(a)){
                return a+"|"+b;
            }else{
                return b;
            }
        });
        System.out.println("连接字符串="+reduce);



        //归集(toList/toSet/toMap)
        List<Integer> list8 = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew2 = list8.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list8.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew2);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);

        //统计(count/averaging)
        //计数：count
        //平均值：averagingInt、averagingLong、averagingDouble
        //最值：maxBy、minBy
        //求和：summingInt、summingLong、summingDouble
        //统计以上所有：summarizingInt、summarizingLong、summarizingDouble
        List<Person> personList2 = new ArrayList<Person>();
        personList2.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList2.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        // 求总数
        long count2 = personList.size();
        // 求平均工资
        Double average = personList2.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> max6 = personList2.stream().map(Person::getSalary).max(Integer::compare);
        // 求工资之和
        int sum4 = personList2.stream().mapToInt(Person::getSalary).sum();
        // 一次性统计所有信息
        //{count=2, sum=15900.000000, min=7000.000000, average=7950.000000, max=8900.000000}
        DoubleSummaryStatistics collect = personList2.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工总数：" + count2);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工最高工资：" + max6.get());
        System.out.println("员工工资总和：" + sum4);
        System.out.println("员工工资所有统计：" + collect);

        //分组(partitioningBy/groupingBy)
        //分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
        //分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
        List<Person> personList3 = new ArrayList<Person>();
        personList3.add(new Person("Tom", 8900, 23, "male", "Washington"));
        personList3.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList3.add(new Person("Lily", 7800, 21, "female", "New York"));
        personList3.add(new Person("Anni", 8200, 24, "female", "New York"));
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList3.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList3.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList3.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);

        //接合(joining) joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串
        List<Person> personList4 = new ArrayList<Person>();
        personList4.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList4.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList4.add(new Person("Lily", 7800, 21, "female", "Washington"));
        String names = personList4.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list9 = Arrays.asList("A", "B", "C");	
        String string = list9.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);

        //排序(sorted)
        List<Person> personList5 = new ArrayList<Person>();
        personList5.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList5.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList5.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList5.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList5.add(new Person("Alisa", 9000, 26, "female", "New York"));
        // 按工资升序排序（自然排序）
        List<String> newList = personList5.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList5.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList5.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);


        //提取/组合
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        Stream<String> stream7 = Stream.of(arr1);
        Stream<String> stream8 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList5 = Stream.concat(stream7, stream8).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect3 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());
        System.out.println("流合并：" + newList5);
        System.out.println("limit：" + collect2);
        System.out.println("skip：" + collect3);

        //分页操作
        //将如下的数组从小到大进行排序，排序完成之后，从第1行开始，查询10条数据出来，操作如下：
        //需要查询的数据
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5, 10, 6, 20, 30, 40, 50, 60, 100);
        List<Integer> dataList = numbers.stream().sorted(Integer::compareTo).skip(0).limit(10).collect(Collectors.toList());
        System.out.println(dataList.toString());


        //并行操作
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 采用并行计算方法，获取空字符串的数量
        long count3 = strings.parallelStream().filter(String::isEmpty).count();
        System.out.println(count3);

        //集合转Map操作
        List<Person> personList6 = new ArrayList<>();
        personList6.add(new Person("Tom",7000,25,"male","安徽"));
        personList6.add(new Person("Jack",8000,30,"female","北京"));
        personList6.add(new Person("Lucy",9000,40,"male","上海"));
        personList6.add(new Person("Airs",10000,40,"female","深圳"));
        //三个参数分别是 key  value 规则
        Map<Integer, Person> collect4 = personList6.stream().collect(Collectors.toMap(Person::getAge, v -> v, (k1, k2) -> k1));
        System.out.println(collect4);







    }
}
