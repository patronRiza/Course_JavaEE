package ru.jrp.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;

    //private Date dateOfCreation;
    //private Date dateOfUpdate;


    public User(String email, String password, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
