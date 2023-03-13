package com.example.application.presentation;

import com.example.application.business_logic.RegisterBLL;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.SQLException;


@Route("register")
@PageTitle("Register")
public class RegisterView extends VerticalLayout {
    private final TextField name = new TextField("Name");
    private final TextField username = new TextField("Username");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField passwordConfirm = new PasswordField("Confirm password");
    private final Button submitBtn = new Button("Sign Up");
    private final Notification notificationToComplete = new Notification();
    private final Notification notificationToPassword = new Notification();

    public RegisterView() {
        addClassName("register-view");
        getStyle().set("background", "url(https://iili.io/HcXJVtt.jpg)");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getThemeList().set("blue", true);
        H2 title = new H2("Registration");
        setRequiredIndicatorVisible(name, username, email, password, passwordConfirm);

        notificationToComplete.setText("All fields must be completed!");
        notificationToComplete.setPosition(Notification.Position.MIDDLE);
        notificationToComplete.setDuration(2000);
        notificationToComplete.addThemeVariants(NotificationVariant.LUMO_ERROR);

        notificationToPassword.setText("Passwords are different, repeat them.");
        notificationToPassword.setPosition(Notification.Position.MIDDLE);
        notificationToPassword.setDuration(2000);
        notificationToPassword.addThemeVariants(NotificationVariant.LUMO_ERROR);

        name.getStyle().set("width", "300px");
        username.getStyle().set("width", "300px");
        email.getStyle().set("width", "300px");
        password.getStyle().set("width", "300px");
        passwordConfirm.getStyle().set("width", "300px");
        submitBtn.getStyle().set("width", "180px");
        submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitBtn.addClickShortcut(Key.ENTER);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(
                title,
                name,
                username,
                email,
                password,
                passwordConfirm,
                submitBtn
        );
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        verticalLayout.getThemeList().set("dark", true);
        add(verticalLayout);
        verticalLayout.setMaxWidth("400px");
        verticalLayout.setMaxHeight("650px");
        submitBtn.addClickListener(e -> {
            if (name.getValue().equals("") || username.getValue().equals("") || email.getValue().equals("") || password.getValue().equals("") || passwordConfirm.getValue().equals("")) {
                notificationToComplete.open();
            } else if (!password.getValue().equals(passwordConfirm.getValue())) {
                    notificationToPassword.open();
            } else {
                UI.getCurrent().navigate(LogInView.class);
                RegisterBLL reg = new RegisterBLL();
                try {
                    reg.register(name.getValue(), username.getValue(), password.getValue(), email.getValue());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });
    }

    private void setRequiredIndicatorVisible(TextField name, TextField username, EmailField email, PasswordField
            password, PasswordField passwordConfirm) {
        name.setRequiredIndicatorVisible(true);
        username.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        password.setRequiredIndicatorVisible(true);
    }
}
