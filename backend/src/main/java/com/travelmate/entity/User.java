package com.travelmate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String name;

    public User(
            String loginId,
            String password,
            String name
    ){
        this.loginId=loginId;
        this.password=password;
        this.name=name;
    }
}
