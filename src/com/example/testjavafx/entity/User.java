package com.example.testjavafx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Group 10
 * Date: 2022-06-30
 * Time: 12:01 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue

    @Type(type = "uuid-char")
    private UUID id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;


}
