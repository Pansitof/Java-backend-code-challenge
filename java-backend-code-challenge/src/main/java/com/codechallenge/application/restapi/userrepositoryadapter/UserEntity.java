package com.codechallenge.application.restapi.userrepositoryadapter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class UserEntity {
    @Id
    private String username;
    private String name;
    private String email;
    private String gender;
    private String picture;

    public UserEntity(){}
    public UserEntity(String username, String name, String email, String gender, String picture) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.picture = picture;
    }

    public String username() {return username;}

    public void changeUsername(String username) {this.username = username;}

    public String name() {return name;}

    public void changeName(String name) {this.name = name;}

    public String email() {return email;}

    public void changeEmail(String email) {this.email = email;}

    public String gender() {return gender;}

    public void changeGender(String gender) {this.gender = gender;}

    public String picture() {return picture;}

    public void changePicture(String picture) {this.picture = picture;}

    @Override
    public String toString() {
        return "User{\n" +
                "   username='" + username + "',\n" +
                "   name='" + name + "',\n" +
                "   email='" + email + "',\n" +
                "   gender='" + gender +  "',\n" +
                "   pic='" + picture + "',\n" +
                "}\n";
    }
}
