package com.example.testjavafx.controller;

import com.example.testjavafx.store.CurrentUser;
import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    public Text greeting;
    @FXML
    private Button btnBack;

    @FXML
    void btnBack_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/LoginView.fxml", pane, this);

    }

    public VBox pane;

    @FXML
    private Button btnCouponCount;

    @FXML
    private Button btnSetCouponCount;

    @FXML
    private Button btnStockAvailability;

    @FXML
    private Button btnUpdateStock;

    @FXML
    void btnCouponCount_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/CouponCountView.fxml", pane, this);

    }

    @FXML
    void btnSetCouponCount_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/UpdateCouponCount.fxml", pane, this);


    }

    @FXML
    void btnStockAvailability_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/StocksView.fxml", pane, this);


    }

    @FXML
    void btnUpdateStock_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/UpdateStockView.fxml", pane, this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        greeting.setText("Hi " + CurrentUser.get().getName());
    }
}

