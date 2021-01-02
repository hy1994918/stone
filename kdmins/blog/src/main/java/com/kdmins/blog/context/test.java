package com.kdmins.blog.context;

import java.util.*;

public class test {
    public static void main(String[] args) {
        outMaxStr("aawweeew");
    }


    public static void outMaxStr(String str){
        /*hashmap快速统计字符串出现的次数*/
        /*k是字符串  v是出现的次数*/
        HashMap<Character, Integer> strMap=new HashMap();
        for(int index=0;index<str.length();index++){
            if(strMap.get(str.charAt(index))==null){
                strMap.put(str.charAt(index),0);
            }else{
                strMap.put(str.charAt(index),strMap.get(str.charAt(index))+1);
            }
        }
        /*然后对其进行排序*/
        ArrayList<Map.Entry<Character,Integer>>  sort=new ArrayList(strMap.entrySet());
        Collections.sort(sort, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue()-o1.getValue();//降序排序
            }
        });

        for (Map.Entry<Character, Integer> e: sort) {

            if(e.getValue().equals(sort.get(0).getValue())){
                /*等出现次数等于第一个数是即出现次数最多*/
               System.out.println(e.getKey());
            }

        }
    }



}
