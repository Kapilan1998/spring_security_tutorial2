package com.spring.security.spring.security.practical.entity;

import com.spring.security.spring.security.practical.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String password;
    //    @Enumerated(EnumType.ORDINAL)       //by default this will consider as default and relevant field number will be saved to db
    @Enumerated(EnumType.STRING)
    private Role role;


}
