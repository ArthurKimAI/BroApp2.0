package com.example.broapp20;

public class CustomerModel {
    private String Name;
    private double time;
    private double date;

    @Override
    public String toString() {
        return "CustomerModel{" +
                "Name='" + Name + '\'' +
                ", time=" + time +
                ", date=" + date +
                '}';
    }

    public CustomerModel(String name, double time, double date) {
        Name = name;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }
}
