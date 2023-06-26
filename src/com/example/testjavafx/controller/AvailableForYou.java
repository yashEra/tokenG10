package com.example.testjavafx.controller;

import com.example.testjavafx.db.HibernateConfig;
import com.example.testjavafx.entity.Stock;
import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.val;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class AvailableForYou implements Initializable {
    public VBox pane;

    @FXML
    private Button btnBack;

    @FXML
    private Text count;

    @FXML
    void onClickBack(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/UserView.fxml", pane, this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            val stocks = session.createQuery("SELECT s from Stock s", Stock.class).getResultList();
            Stock stock = stocks.get(0);
            double stockPerUser = stock.getStockPerUser();
            count.setText(Double.toString(stockPerUser));

        }
    }
}
