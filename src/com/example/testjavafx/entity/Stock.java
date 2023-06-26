package com.example.testjavafx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
public class Stock {
    @Id
    @GeneratedValue

    @Type(type = "uuid-char")
    private UUID id;
    @Column
    private double value;

    @ColumnDefault("1")
    @Column
    private double stockPerUser;

}
