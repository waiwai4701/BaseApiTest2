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
public class StreamTest {

    public static void main(String[] args) {

        //-----stream的创建----
        //list创建顺序流和并行流


        List<String> listn = Arrays.asList("a","b","c");
        Stream<String> stream = listn.stream();
        Stream<String> paralleStream = listn.parallelStream();
        //array创建流
        int[] array = {1,3,5,6,8};
        IntStream stream2 = Arrays.stream(array);


        //静态创建stream  of() iterate() generate()

        Stream<Integer> stream3 = Stream.of(1,2,3,4,5,6);

        Stream<Integer> stream4 = Stream.iterate(0,(x)->x+3).limit(4);
        stream4.forEach(System.out::print);
        System.out.println();

        Stream<Double> stream5 = Stream.generate(Math::random).limit(3);
        stream5.forEach(System.out::print);
        System.out.println();

        //stream
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        //filter
        list.stream().filter(x->x>6).forEach(System.out::print);
        //匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x->x>6).findFirst();
        System.out.println("匹配第一个："+findFirst.get());
        //匹配任何一个，适用于并行流
        Optional<Integer> findAny = list.parallelStream().filter(x->x>6).findAny();
        System.out.println("匹配任何一个："+findAny.get());
        //是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x->x<6);
        System.out.println("anyMatch:"+anyMatch);

        //最长字符串
        List<String> list3 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list3.stream().max(Comparator.comparing(String::length));
        System.out.println("最长字符串："+max.get());

        //最大整数
        List<Integer> list4 = Arrays.asList(7, 6, 9, 4, 11, 6);
        Optional<Integer> max2 = list4.stream().max(Integer::compareTo);
        System.out.println("max2="+max2.get());

        //自定义排序
        Optional<Integer> max3 = list4.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("自定义最大排序："+max3.get());

        //计算Integer集合大于6的元素个数
        List<Integer> list5 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list5.stream().filter(x->x>6).count();
        System.out.println("list5中大于6的元素个数是："+count);

        //数组每个元素大写，并转换成list
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写："+strList);

        //list每个元素+3
        //map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x->x+3).collect(Collectors.toList());
        System.out.println("每个元素+3："+intListNew);

        //将两个字符数组合并成一个新的字符数组
        List<String> list6 = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list6.stream().flatMap(s->{
            //将每个元素转换成一个stream
            String[] Split = s.split(",");
            Stream<String> s2 = Arrays.stream(Split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("字符数组合并："+listNew);

        //规约
        //Lambda反复结合每个元素，直到流被归约成一个值
        List<Integer> list7 = Arrays.asList(1, 3, 2, 8, 11, 4);
        //求和1
        Optional<Integer> sum = list7.stream().reduce(Integer::sum);
        System.out.println("sum="+sum);
        //求和2,带初始值
        Optional<Integer> sum2 = Optional.ofNullable(list7.stream().reduce(1, Integer::sum));
        System.out.println("sum2="+sum2);
        //求乘积
        Optional<Integer> product = list7.stream().reduce((x,y)->x*y);
        System.out.println("product="+product);
        //求最大值
        Optional<Integer> max4 = list7.stream().reduce((x,y)->x>y?x:y);
        System.out.println("max4="+max4);
        //求最大值2
        Optional<Integer> max5 = list7.stream().reduce(Integer::max);
        System.out.println("max5="+max5);
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

        //归集 toList toSet toMap
        List<Integer> list8 = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew2 = list8.stream().filter(x->x%2==0).collect(Collectors.toList());
        Set<Integer> set = list8.stream().filter(x->x%2 != 0).collect(Collectors.toSet());
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        Map<String,Person> map = personList.stream().filter(p->p.getSalary()>8000).collect(Collectors.toMap(Person::getName,p->p));
        System.out.println("map="+map);

        //统计
        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList2.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        //求平均工资
        Double avg = personList2.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("avg salary="+avg);
        //求最高工资
        Optional<Integer> max6 = personList2.stream().map(Person::getSalary).max(Integer::compareTo);
        System.out.println("max6="+max6);
        //求工资之和
        int sum4 = personList2.stream().mapToInt(Person::getSalary).sum();
        System.out.println("sum4="+sum4);
        //一次性统计所有信息
        //{count=2, sum=15900.000000, min=7000.000000, average=7950.000000, max=8900.000000}
        DoubleSummaryStatistics collect = personList2.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("sumarry statistics="+collect);

        //分组 分区
        List<Person> personList3 = new ArrayList<Person>();
        personList3.add(new Person("Tom", 8900, 23, "male", "Washington"));
        personList3.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList3.add(new Person("Lily", 7800, 21, "female", "New York"));
        personList3.add(new Person("Anni", 8200, 24, "female", "Los Age"));
        //将员工按薪资是否高于8000分组
        Map<Boolean,List<Person>> part = personList3.stream().collect(Collectors.partitioningBy(x->x.getSalary() > 8000));
        System.out.println("按薪资大于8000分组情况="+part);
        //将员工按性别分组
        Map<String, List<Person>> group = personList3.stream().collect(Collectors.groupingBy(Person::getSex));
        System.out.println("按性别分组情况="+group);
        //将员工先按性别分组再按地区分组
        Map<String,Map<String, List<Person>>> group2 = personList3.stream().collect(Collectors.groupingBy(Person::getSex,Collectors.groupingBy(Person::getArea)));
        System.out.println("先按性别再按地区分组="+group2);

        //接合 joining
        List<Person> personList4 = new ArrayList<Person>();
        personList4.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList4.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList4.add(new Person("Lily", 7800, 21, "female", "Washington"));
        String names = personList4.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("joining names="+names);
        List<String> list9 = Arrays.asList("A","B","C");
        String string = list9.stream().collect(Collectors.joining(","));
        System.out.println("joining2="+string);

        //排序
        List<Person> personList5 = new ArrayList<Person>();
        personList5.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList5.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList5.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList5.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList5.add(new Person("Alisa", 9000, 26, "female", "New York"));
        //按工资的升序排序
        List<String> newList = personList5.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序排序="+newList);
        //按工资的倒叙排序
        List<String> newList2 = personList5.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资倒叙排序="+newList2);
        //先按工资再按年龄升序排序
        List<String> newList3 = personList5.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName).collect(Collectors.toList());
        System.out.println("先按工资再按年龄升序排序="+newList3);
        //先按工资再按年龄自定义排序
        List<String> newList4 = personList5.stream().sorted((p1,p2)->{
            if(p1.getSalary() == p2.getSalary()){
                return p2.getAge() - p1.getAge();
            }else{
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("先按工资再按年龄自定义排序="+newList4);

        //提取 组合
        String[] arr1 = {"a","b","c","d"};
        String[] arr2 = {"d","e","f","g"};
        Stream<String> stream7 = Arrays.stream(arr1);
        Stream<String> stream8 = Arrays.stream(arr2);
        //合并两个流并去重
        List<String> newList5 = Stream.concat(stream7, stream8).distinct().collect(Collectors.toList());
        System.out.println("合并两个流并去重:"+newList5);
        //限制从流中获取前n个数据
        List<Integer> collect2 = Stream.iterate(1,x->x+2).limit(10).collect(Collectors.toList());
        System.out.println("限制从流中获取前n个数据="+collect2);
        //跳过前n个数据
        List<Integer> collect3 = Stream.iterate(1,x->x+2).skip(1).limit(5).collect(Collectors.toList());
        System.out.println("跳过前n个数据="+collect3);
        //分页操作
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5, 10, 6, 20, 30, 40, 50, 60, 100);
        List<Integer> dataList = numbers.stream().sorted(Integer::compareTo).skip(2).limit(10).collect(Collectors.toList());
        System.out.println("分页操作="+dataList);

        //并行操作，获取空字符串的数量
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count3 = strings.stream().filter(String::isEmpty).count();
        System.out.println("并行操作，获取空字符串的数量="+count3);

        //集合转化成map操作
        List<Person> personList6 = new ArrayList<>();
        personList6.add(new Person("Tom",7000,25,"male","安徽"));
        personList6.add(new Person("Jack",8000,30,"female","北京"));
        personList6.add(new Person("Lucy",9000,40,"male","上海"));
        personList6.add(new Person("Airs",10000,40,"female","深圳"));

        Map<Integer,Person> collect4 = personList6.stream().collect(Collectors.toMap(Person::getAge,v->v,(k1,k2)->k1));
        System.out.println("集合转化成map操作="+collect4);









    }
}
