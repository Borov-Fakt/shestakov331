package org.example;

public class User {
    private int id;
    private String name;
    private int age;
    private boolean isMale;

    public User(int id, String name, int age, boolean isMale) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return isMale;
    }

    @Override
    public String toString() {
        String genderString;
        if (isMale) {
            genderString = "man";
        } else {
            genderString = "woman";
        }
        return "User{id=" + id + ", name='" + name + "', age=" + age + ", gender='" + genderString + "'} ";
    }
}

