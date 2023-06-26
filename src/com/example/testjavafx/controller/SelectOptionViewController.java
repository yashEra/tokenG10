package com.example.testjavafx.controller;

import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectOptionViewController implements Initializable {

    public VBox pane;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnUser;

    @FXML
    void btnAdmin_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/AdminView.fxml", pane, this);

    }

    @FXML
    void btnUser_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/UserView.fxml", pane, this);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

