package com.example.testjavafx.controller;

import com.example.testjavafx.db.HibernateConfig;
import com.example.testjavafx.entity.Coupon;
import com.example.testjavafx.entity.CouponCode;
import com.example.testjavafx.entity.Stock;
import com.example.testjavafx.store.CurrentUser;
import com.example.testjavafx.util.OptionPane;
import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.Session;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class GetTokenViewController implements Initializable {
    public VBox pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnBack_onClick(ActionEvent actionEvent) {

        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/UserView.fxml", pane, this);
    }

    public void btnGenerate_onClick(ActionEvent actionEvent) {

        CouponCode couponCode;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            val stocks = session.createQuery("SELECT s from Stock s", Stock.class).getResultList();
            Stock stock = stocks.get(0);
            try{


                LocalDate today = LocalDate.now();
                LocalDate yesterday = today.minusDays(1);
                List<CouponCode> coupon = session.createQuery("SELECT c from CouponCode c " +
                        "where c.user.id =:userId and c.date > :date " +
                        "order by c.date desc", CouponCode.class).setParameter("userId", CurrentUser.get().getId())
                        .setParameter("date", yesterday)
                        .getResultList();


                if(coupon.size() >= 1) {
                    OptionPane.showErrorAtSide("Already got coupon");
                    return;
                }
            }catch (Exception e){
                log.warn(e.getMessage());
            }

            couponCode = new CouponCode();
            couponCode.setUser(CurrentUser.get());
            couponCode.setDate(LocalDate.now());
            couponCode.setCount(stock.getStockPerUser());
            session.persist(couponCode);


            Coupon coupon = session.createQuery("SELECT c from Coupon c", Coupon.class).getSingleResult();
            coupon.setCount(coupon.getCount() - 1);
            session.persist(coupon);

            stock.setValue(stock.getValue() - stock.getStockPerUser());
            session.persist(stock);

            session.getTransaction().commit();

        }
        OptionPane.showDoneAtSide("Your Coupon Code is " + couponCode.getId());
    }
}
