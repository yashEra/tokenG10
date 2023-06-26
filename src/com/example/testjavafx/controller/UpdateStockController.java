package com.example.testjavafx.controller;

import com.example.testjavafx.db.HibernateConfig;
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

public class UpdateStockController implements Initializable {

    @FXML
    public VBox pane;
    public TextField txtStock;
    public TextField txtTokenPerDayPerUser;

    @FXML
    private Button btnUpdateStock;

    @FXML
    private Button btnBack;

    @FXML
    void btnBack_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/AdminView.fxml", pane, this);

    }

    @FXML
    void btnUpdateStock_onClick(ActionEvent event) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();

                Stock stock = session.createQuery("select s from Stock s", Stock.class).getSingleResult();
                stock.setValue(Double.parseDouble(txtStock.getText()));
                stock.setStockPerUser(Double.parseDouble(txtTokenPerDayPerUser.getText()));
                txtStock.getText();
                session.persist(stock);

            session.getTransaction().commit();
            OptionPane.showDoneAtSide("Update Successful!");

        } catch(Exception e){
            OptionPane.showErrorAtSide("Invalid Input");
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try{
                Stock stock = session.createQuery("select s from Stock s", Stock.class).getSingleResult();
                Double value = stock.getValue();
                Double tokenPerDayPerUser = stock.getStockPerUser();
                txtStock.setText(value.toString());
                txtTokenPerDayPerUser.setText(tokenPerDayPerUser.toString());
            }catch(Exception e){
                session.persist(new Stock());
                txtStock.setText("0");
            }
            session.getTransaction().commit();

        }

    }
}
