/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/

package com.example.group10hw03;

import java.io.Serializable;

public class User implements Serializable {
    String gender;
    Integer weight;

    public User(String gender, Integer weight) {
        this.gender = gender;
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "gender='" + gender + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Integer getWeight() {
        return weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
