package com.example.testjavafx.controller;

import com.example.testjavafx.db.HibernateConfig;
import com.example.testjavafx.entity.Role;
import com.example.testjavafx.entity.User;
import com.example.testjavafx.util.OptionPane;
import com.example.testjavafx.util.ScreenLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountView implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private RadioButton checkAdmin;

    @FXML
    private RadioButton checkUser;

    public VBox pane;
    @FXML
    private Button btnCreate;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    void btnCreate_onClick(ActionEvent event) {
        // Validation
        String email = txtEmail.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        if(!password.equals(confirmPassword)){
            OptionPane.showErrorAtSide("Confirm Password and Password are not equal");
            return;
        }
        if(email.isEmpty()){
            OptionPane.showErrorAtSide("email Required");
            return;
        }
        if(name.isEmpty()){
            OptionPane.showErrorAtSide("Name Required");
            return;
        }
        if(phoneNumber.isEmpty()){
            OptionPane.showErrorAtSide("Phone Number Required");
            return;
        }
        if(password.isEmpty()){
            OptionPane.showErrorAtSide("Password Required");
            return;
        }

        Role role = checkAdmin.isSelected() ? Role.ADMIN : Role.USER;

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setRole(role);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);

        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(newUser);
            session.getTransaction().commit();
            OptionPane.showDoneAtSide("Account Created!");
            ScreenLoader.getInstance().loadPanel(
                    "/com/example/testjavafx/view/LoginView.fxml", pane, this);
        }
    }
    @FXML
    void btnBack_onClick(ActionEvent event) {
        ScreenLoader.getInstance().loadPanel(
                "/com/example/testjavafx/view/LoginView.fxml", pane, this);

    }

    @FXML
    void txtConfirmPassword_onAction(ActionEvent event) {
            btnCreate.fire();

    }

    @FXML
    void txtEmail_onAction(ActionEvent event) {
            txtName.requestFocus();
    }

    @FXML
    void txtName_onAction(ActionEvent event) {
            txtPhoneNumber.requestFocus();
    }

    @FXML
    void txtPassword_onAction(ActionEvent event) {
            txtConfirmPassword.requestFocus();
    }

    @FXML
    void txtPhoneNumber_onAction(ActionEvent event) {
            txtPassword.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtEmail.requestFocus();

    }
}
