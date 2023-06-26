package com.example.testjavafx.controller;

import com.example.testjavafx.db.HibernateConfig;
import com.example.testjavafx.entity.Stock;
import com.example.testjavafx.model.TableProp;
import com.example.testjavafx.util.ScreenLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.val;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StockViewController implements Initializable {

    public VBox pane;
    public TableView<TableProp> table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("value"));
        getData();
    }

    private void getData() {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            val data = session.createQuery("SELECT s from Stock s", Stock.class).getResultList();
            table.setItems(
                    FXCollections.observableArrayList(
                    data.stream().map((e)-> new TableProp(e.getValue())).collect(Collectors.toList())
            )
            );

        }
    }

    @FXML
    private Button btnBack;

    @FXML
    void btnBack_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/AdminView.fxml", pane, this);

    }

}
