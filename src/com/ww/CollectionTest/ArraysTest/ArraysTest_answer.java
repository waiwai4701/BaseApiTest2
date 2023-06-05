package com.ww.CollectionTest.ArraysTest;

import java.util.Arrays;

public class ArraysTest_answer {

    public static void main(String[] args) {

        //排序
        int[] array = {1,5,2,4,5,2,8};
        Arrays.sort(array);
        System.out.println("sort="+Arrays.toString(array));

        //并行排序
        array = new int[]{1, 5, 2, 4, 5, 2, 8};
        Arrays.parallelSort(array);
        System.out.println("parallelSort="+Arrays.toString(array));

        //判断两个数组相等
        int[] array2 = {1,5,2,4,5,2,8};
        System.out.println("equals="+array.equals(array2));

        //全部替换数据中的某个值
        //参考：https://blog.csdn.net/fengqilai112/article/details/100667279

        Arrays.fill(array2 ,9);
        System.out.println("fill="+Arrays.toString(array2));
        //部分替换成某个值
        Arrays.fill(array2,3,4,10);
        System.out.println("fill2="+Arrays.toString(array2));

        //复制
        //copyOf()的第二个自变量指定要建立的新数组长度，如果新数组的长度超过原数组的长度，则保留数组默认值
        //参考：https://blog.csdn.net/qq_25131363/article/details/85001414
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = Arrays.copyOf(arr1, 5);
        int[] arr3 = Arrays.copyOf(arr1, 10);
        System.out.println("copyOf1="+Arrays.toString(arr2));
        System.out.println("copyOf2="+Arrays.toString(arr3));

        //按下标复制

        int[] arr4 = Arrays.copyOfRange(arr1,1,2);
        System.out.println("copyOfRange="+Arrays.toString(arr4));

        //深度相等,主要用于多维数组
        int[][] array3 = {{1,2},{3,4},{5,6},{7,8}};
        int[][] array4 = {{1,2},{3,4},{5,6},{7,8}};
        System.out.println("equal2="+array3.equals(array4));
        System.out.println("Arrays.equals="+Arrays.equals(array3,array4));
        System.out.println("Arrays.deepEquals="+Arrays.deepEquals(array3,array4));

        //转换成string
        System.out.println("toString="+Arrays.toString(array3));

        //深度转换成string
        System.out.println("deepToString="+Arrays.deepToString(array3));

        //setAll 设置一个对每个元素的变化公式
        int[] array5 = {1,2,3,4};
        Arrays.setAll(array5,x->x*10);
        System.out.println("setAll="+Arrays.toString(array5));

    }
}
