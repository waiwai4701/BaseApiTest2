package com.ww.StringTest;

import java.nio.charset.StandardCharsets;

public class StringTest_answer {

    public static void main(String[] args) {
        //1 长度
        String s1 = "abc";
        System.out.println("length="+s1.length());

        //2 是否为空
        System.out.println("isEmpty="+s1.isEmpty());

        //3 某个下标的字符
        System.out.println("下标字符="+s1.charAt(1));

        //4 转换编码格式
        System.out.println("转换编码"+s1.getBytes(StandardCharsets.UTF_8));

        //5 相等
        String s2 = "abc";
        System.out.println("equals="+s1.equals(s2));

        //6 不区分大小写的相等
        String s3 = "Abc";
        System.out.println("equalIngnoreCase="+s1.equalsIgnoreCase(s3));

        //7 comparator接口返回int值的写法
        System.out.println("compareTo="+s1.compareTo(s3));

        //8 以。。开头
        System.out.println("startWith="+s1.startsWith("ab"));

        //9 以。。结尾
        System.out.println("endWith="+s1.endsWith("c"));

        //10 某个字符/数字的下标
        System.out.println("indexOf="+s1.indexOf("b"));

        //11 某个字符/数字的最后一个下标
        String s4 = "abca";
        System.out.println("lastIndexOf="+s4.lastIndexOf("a"));

        //12 从某个下标截取
        System.out.println("split="+s1.substring(1));

        //13 从某个下标截取一个长度 beginIndex, endIndex
        System.out.println("split2="+s1.substring(1,1));

        //14 拼接字符串
        System.out.println("concat="+s1.concat(s2));

        //15 替换某个字符
        System.out.println("replace="+s4.replace("a","0"));

        //16 替换首个字符
        System.out.println("replaceFirst="+s4.replaceFirst("a","0"));

        //17 替换全部字符
        System.out.println("replaceAll="+s4.replaceAll("a","0"));

        //18 是否符合某个表达式matches
        //https://blog.csdn.net/juzixiansheng/article/details/87867118
        System.out.println("matches="+s1.matches("^[A-Za-z]+$"));//只能输入由26个英文字母组成的字符串

        //19 是否包含另外一个字符串
        System.out.println("contains="+s1.contains("ab"));

        //20 把某个字符串替换为另外一个字符串
        System.out.println("replaceCharSequence="+s1.replace("ab","00"));

        //21 把字符串按某个符号切割成字符串数组
        String s5 = "a,b,c,d,e";
        String[] array = s5.split(",");
        System.out.println("split="+array.length);

        //22 拼接多个字符串
        System.out.println("joinArray="+String.join("-",array));

        //23 转换成小写
        System.out.println("toLowerCase="+s3.toLowerCase());

        //24 转换成大写
        System.out.println("toUpperCase="+s3.toUpperCase());

        //25 去除空格 只能去除前后
        String s6= "a b c d e";
        System.out.println("trim="+s6.trim());

        //26 字符串转换成字符数组
        char[] charArray = s1.toCharArray();
        System.out.println("toCharArray="+charArray.length);

        //27 转换成某个格式，多用于日期转换
        String s7 = String.format("Hi,%s","小超");
        System.out.println("format="+s7);

        //28 字符数组  布尔 long float double转换成字符串
        System.out.println("charArray to String="+String.valueOf(charArray));
        System.out.println("boolean to String="+String.valueOf(false));
        System.out.println("long to String="+String.valueOf(1l));
        System.out.println("float to String="+String.valueOf(1f));
        System.out.println("double to String="+String.valueOf(1.0d));






    }

}
