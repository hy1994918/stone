package com.kdmins.blog.pojo;

public class Student {
    String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().getName()==this.getClass().getName()){
            return this.getName()==((Student)obj).getName();
        }
        return false;

    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student student1=new Student("小王");
        Student student2=new Student("小王");
        System.out.println(student2.equals(student1));
    }
}
