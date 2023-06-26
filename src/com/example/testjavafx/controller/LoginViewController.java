package com.example.testjavafx.controller;


import com.example.testjavafx.db.HibernateConfig;
import com.example.testjavafx.entity.Role;
import com.example.testjavafx.entity.User;
import com.example.testjavafx.store.CurrentUser;
import com.example.testjavafx.util.OptionPane;
import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    public VBox pane;
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblOutput;

    @FXML
    void btnLogin_onAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();


        try(Session currentSession = sessionFactory.openSession()) {
            User singleResult = currentSession.createQuery("SELECT u from User u where u.email =:email and u.password =:password", User.class)
                    .setParameter("email", username)
                    .setParameter("password", password).getSingleResult();


            OptionPane.showDoneAtSide("Login Success");
            Role role = singleResult.getRole();
            CurrentUser.set(singleResult);
            if(role == Role.ADMIN){
                            ScreenLoader.getInstance().loadPanel(
                    "/com/example/testjavafx/view/AdminView.fxml", pane, this);
            }else {
                ScreenLoader.getInstance().loadPanel(
                        "/com/example/testjavafx/view/UserView.fxml", pane, this);
            }
        }catch (Exception e){
            e.printStackTrace();
            OptionPane.showErrorAtSide("The email or password is invalid");
        }


    }

    @FXML
    void txtPassword_onAction(ActionEvent event) {
        btnLogin.fire();
    }

    @FXML
    void txtUsername_onAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void createAccount()
    {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/CreateAccountView.fxml", pane, this);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Run the method for the first time.");
    }
}