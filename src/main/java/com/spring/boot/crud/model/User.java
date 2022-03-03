package com.spring.boot.crud.model;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    private  UUID userUID;
    private  String firstName;
    private  String lastName;
    private  Gender gender;
    private  Integer age;
    private  String email;


    public User(UUID userUid, String firstName, String lastName, Gender gender, Integer age, String email) {
        this.userUID = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public enum Gender {
        MAMLE,
        FEMALE
    }
}
