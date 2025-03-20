package com.codechallenge.application.domain;

public record User(String username,
                   String name,
                   String email,
                   String gender,
                   String picture) {
}
/*
class User2 {
    private Email email;

    public User2(Email email){
        this.email=email;
    }

    public Email getEmail(){
        return email;
    }

    public static void main(String[] args){
        Email value = new Email("value");
        User2 newUser = new User2(null);
        String variable = newUser.getEmail().value();
    }
}

record Email(String value){

}
*/