package com.java_backend_code_challenge.user;

public class User {
    private String username;
    private String name;
    private String email;
    private String gender;
    private String picture;

    public User(String username,String name, String email, String gender, String picture) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.picture = picture;
    }

    public String username() {
        return username;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public String name() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String email() {
        return email;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public String gender() {
        return gender;
    }

    public void changeGender(String gender) {
        this.gender = gender;
    }

    public String picture() {
        return picture;
    }

    public void changePicture(String picture) {
        this.picture = picture;
    }
}
