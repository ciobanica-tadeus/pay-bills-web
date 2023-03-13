package com.example.application.presentation;

import com.example.application.business_logic.RegisterBLL;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import java.sql.SQLException;

public class RegistrationForm extends VerticalLayout {
    private final TextField name = new TextField("Name");;
    private final TextField username = new TextField("Username");;
    private final EmailField email = new EmailField("Email");;
    private final PasswordField password = new PasswordField("Password");;
    private final PasswordField passwordConfirm = new PasswordField("Confirm password");
    private final Button submitBtn = new Button("Sign Up");;
    private final Notification notification = new Notification();

    public RegistrationForm() {
        setRequiredIndicatorVisible(name, username, email,password, passwordConfirm);
        getStyle().set("background","url(https://iili.io/HcXJVtt.jpg)");
        setSizeFull();
        notification.setText("All fields must be completed!");
        notification.setPosition(Notification.Position.BOTTOM_CENTER);
        notification.setDuration(2000);

        name.getStyle().set("width","300px");
        username.getStyle().set("width","300px");
        email.getStyle().set("width","300px");
        password.getStyle().set("width","300px");
        submitBtn.getStyle().set("width","180px");
        submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitBtn.addClickShortcut(Key.ENTER);
        add(
           name,
           username,
           email,
           password,
           passwordConfirm,
           submitBtn
        );

        submitBtn.addClickListener(e ->{
            if(name.getValue().equals("") || username.getValue().equals("") || email.getValue().equals("") || password.getValue().equals("") || passwordConfirm.getValue().equals("")){
                notification.open();
            }else{
                UI.getCurrent().navigate(LogInView.class);
                RegisterBLL reg = new RegisterBLL();
                try {
                    reg.register(name.getValue(),username.getValue(), password.getValue(), email.getValue());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });
    }

    private void setRequiredIndicatorVisible(TextField name, TextField username, EmailField email, PasswordField password, PasswordField passwordConfirm) {
        name.setRequiredIndicatorVisible(true);
        username.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        password.setRequiredIndicatorVisible(true);
    }

}
