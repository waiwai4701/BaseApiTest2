package com.ww.NumberTest;

public class NumberTest_answer {
    public static void main(String[] args) {
        //-----Integer------
        //String转Integer valueOf
        System.out.println("valueOf="+Integer.valueOf("11"));

        //int转Integer Integer(int)
        System.out.println("toInteger="+new Integer(1));

        //Integer转short
        System.out.println("shortValue="+new Integer(2).shortValue());

        //Integer转String
        System.out.println("toString="+new Integer(3).toString());

        //比较大小
        System.out.println("compareTo="+new Integer(4).compareTo(new Integer(5)));

        //加法
        System.out.println("sum="+Integer.sum(1,2));

        //最大值
        System.out.println("max="+Integer.max(1,3));

        //最小值
        System.out.println("min="+Integer.min(1,3));


    }
}
