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

public class UserViewController implements Initializable {

    public VBox pane;
    public Text greeting
            ;

    @FXML
    private Button btnBack;

    @FXML
    void btnBack_onCLick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/LoginView.fxml", pane, this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        greeting.setText("Hi " + CurrentUser.get().getName());
    }

    public void openStockPerUser(ActionEvent actionEvent) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/AvailableForYou.fxml", pane, this);
    }

    public void openGetToken(ActionEvent actionEvent) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/GetToken.fxml", pane, this);
    }
}