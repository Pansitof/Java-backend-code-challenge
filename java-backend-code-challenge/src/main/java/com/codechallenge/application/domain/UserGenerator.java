package com.codechallenge.application.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class UserGenerator {
    public User generateAnUser() throws IOException {
        URL url = new URL("https://randomuser.me/api/?inc=name,email,gender");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String usuario = br.readLine();
        br.close();

        String[] array = usuario.split("\"\\W*\"");

        String[] array2 = Arrays.toString(array).split("\\W+\\s*");

        String UserName = array2[8] + array2[10];
        String Name = array2[8];
        String Email = UserName + "@example.com";
        String Genre = array2[3];
        String Picture = Name+"_"+new NumberGenerator().generateFourRandomsDigits();
        return new User(UserName,Name,Email,Genre,Picture);
    }
}
