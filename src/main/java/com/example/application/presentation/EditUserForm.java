package com.example.application.presentation;

import com.example.application.business_logic.ProfileBL;
import com.example.application.model.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditUserForm extends VerticalLayout {
    ProfileBL profileBL = ProfileBL.getProfileLogic();
    TextField name = new TextField("Name");
    TextField username = new TextField("Username");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");

    Button save = new Button("Save");
    FormLayout formLayout = new FormLayout();
    private User user;

    public EditUserForm() {
        getStyle().set("margin-left", "15px");
        formLayout.add(
                name,
                username,
                password,
                email,
                createButtonsLayout()
        );
        add(formLayout);

    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getUsername() {
        return username.getValue();
    }

    public void setUsername(String username) {
        this.username.setValue(username);
    }

    public String getEmail() {
        return email.getValue();
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public String getPassword() {
        return password.getValue();
    }

    public void setPassword(String password) {
        this.password.setValue(password);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);
        save.addClickListener(event -> validateAndSave());
        return new HorizontalLayout(save);
    }

    private void validateAndSave() {
        if(!getName().equals("")){
            user.setName(getName());
        }

        if(!username.getValue().equals("")){
            user.setUsername(getUsername());
        }

        if(!password.getValue().equals("")){
            user.setPassword(getPassword());
        }

        if(!email.getValue().equals("")){
            user.setEmail(getEmail());
        }

        if (profileBL.modifyYourAccount(user)) {
            Notification notification = new Notification("Date modificate cu succes!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        } else {
            Notification notification = new Notification("O eroare a intervenit la modificarea datelor!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }


}
