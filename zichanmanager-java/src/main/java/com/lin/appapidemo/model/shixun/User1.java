package com.lin.appapidemo.model.shixun;

public class User1 {
    private String userid;
    private String username;
    private String password;
    private int age;

    public String getUserid() {
        return userid;
    }

    public User1 setUserid(String userid) {
        this.userid = userid;
        return User1.this;
    }

    public String getUsername() {
        return username;
    }

    public User1 setUsername(String username) {
        this.username = username;
        return User1.this;
    }

    public String getPassword() {
        return password;
    }

    public User1 setPassword(String password) {
        this.password = password;
        return User1.this;
    }

    public int getAge() {
        return age;
    }

    public User1 setAge(int age) {
        this.age = age;
        return User1.this;
    }

    @Override
    public String toString() {
        return "User1{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

}



