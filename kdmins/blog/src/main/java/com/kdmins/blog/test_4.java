package com.kdmins.blog;

import java.util.*;

public class test_4 {
    public static void main(String [] args){
        Boolean flag = false;
        if(flag = true){
            System.out.println("true");
        }else{

            System.out.println("false");
        }


    }












    /*移除出现最多的字符*/
    public static String removeMaxChar(String str){
        HashMap<Character, Integer> strMap=new HashMap();
        for(int index=0;index<str.length();index++){
            if(strMap.get(str.charAt(index))==null){
                strMap.put(str.charAt(index),0);
            }else{
                strMap.put(str.charAt(index),strMap.get(str.charAt(index))+1);
            }
        }
        ArrayList<Map.Entry<Character,Integer>>  sort=new ArrayList(strMap.entrySet());
        Collections.sort(sort, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        Set<Character> maxChar=new HashSet();
        for (Map.Entry<Character, Integer> e: sort) {
            if(e.getValue().equals(sort.get(0).getValue())){
                maxChar.add(e.getKey());
            }

        }
        for(Character aaa:maxChar){
            System.out.println(aaa);
            str=str.replaceAll(aaa.toString(),"");
        }

        return str;

    }


    /*分解因式*/
    public static void decompose(int n){System.out.print(n+"=");
        for(int i=2;i<=n;i++){
            while(n%i==0 && n!=i){
                n/=i;    //n=n/i;
                System.out.print(i+"*");
            }
            if(n==i){
                System.out.println(i);
                break;
            }
        }
    }

    /*list排序*/
    public static void sortList(){
        List<Integer> a = new LinkedList<Integer>();
        a.add(1);
        a.add(100);
        a.add(50);
        a.add(21);
        a.add(60);
        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(Integer test:a){
            System.out.println(test);
        }
    }
    private static void swap(Integer m, Integer n) {

    }

   /* public static void main(String[] args) {
      *//*System.out.println( matchStr("((ddddd)))()"));*//*
        Integer aa=new Integer(0);
        HashMap<Character, Integer> strMap=new HashMap();
        strMap.put('a',aa);

        aa=aa+1;

        System.out.println(strMap.get('a'));
    }*/
}
