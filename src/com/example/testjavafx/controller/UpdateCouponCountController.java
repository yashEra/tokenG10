package com.example.testjavafx.controller;

import com.example.testjavafx.db.HibernateConfig;
import com.example.testjavafx.entity.Coupon;
import com.example.testjavafx.entity.Stock;
import com.example.testjavafx.util.OptionPane;
import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCouponCountController implements Initializable {

    public VBox pane;

    public TextField txtCoupon;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnUpdate;

    @FXML
    void btnBack_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/AdminView.fxml", pane, this);

    }

    @FXML
    void btnUpdate_onClick(ActionEvent event) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Coupon coupon= session.createQuery("select c from Coupon c", Coupon.class).getSingleResult();
            coupon.setCount(Double.parseDouble(txtCoupon.getText()));
            txtCoupon.getText();
            session.persist(coupon);

            session.getTransaction().commit();
            OptionPane.showDoneAtSide("Update Successful!");
        }

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try{
                Coupon coupon = session.createQuery("select c from Coupon c", Coupon.class).getSingleResult();
                Double count = coupon.getCount();
                txtCoupon.setText(count.toString());
            }catch(Exception e){
                session.persist(new Coupon());
                txtCoupon.setText("0");
            }
            session.getTransaction().commit();

        }


    }
}