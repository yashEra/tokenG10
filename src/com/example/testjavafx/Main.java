package com.example.testjavafx;

import com.example.testjavafx.db.HibernateConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("tokenG10");
        stage.getIcons().setAll(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("assets/wa.png"))));

        stage.setScene(scene);
        HibernateConfig.getSessionFactory();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}