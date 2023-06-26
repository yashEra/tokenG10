package com.example.testjavafx.db;

import com.example.testjavafx.entity.Coupon;
import com.example.testjavafx.entity.CouponCode;
import com.example.testjavafx.entity.Stock;
import com.example.testjavafx.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateConfig {
    private static SessionFactory sessionFactory;
    static {
        sessionFactory = buildSessionFactory();
    }
    private static SessionFactory buildSessionFactory() {

        StandardServiceRegistry
                registry =
                new StandardServiceRegistryBuilder().loadProperties("application.properties").build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Stock.class)
                .addAnnotatedClass(Coupon.class)
                .addAnnotatedClass(CouponCode.class)
                .buildMetadata();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        return  sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
