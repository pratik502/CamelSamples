package com.cameldemo.rabbitmq;


public class Student{
    private int id;
    private String name;
    private double maths,chem,phy,percentage;
    private int rollNumber;

    public Student(int id, String name, double maths, double chem, double phy, int rollNumber) {
        this.id = id;
        this.name = name;
        this.maths = maths;
        this.chem = chem;
        this.phy = phy;
        this.rollNumber = rollNumber;
    }

    public Student(){

    }

    public double getAndSetCalculatedPercentage(){
        this.percentage= (maths+phy+chem)/300*100;
        return percentage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maths=" + maths +
                ", chem=" + chem +
                ", phy=" + phy +
                ", percentage=" + percentage +
                ", rollNumber=" + rollNumber +
                '}';
    }
}