package com.example.RestfulAPI.entity;

public class Student {
    private int id;
    private String name;
    private int age;
    private String major;
    private double averageGrade;

    public Student() {

    }

    public Student(int id, String name, String major, double averageGrade, int age) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.averageGrade = averageGrade;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
