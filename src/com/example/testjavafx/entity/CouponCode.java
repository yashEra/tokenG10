package com.example.testjavafx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
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
public class CouponCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private double count;

    @ManyToOne
    private User user;

    @Column()
    private LocalDate date;
}
