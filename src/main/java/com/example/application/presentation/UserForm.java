package com.example.application.presentation;

import com.example.application.business_logic.ProfileBL;
import com.example.application.model.User;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class UserForm extends VerticalLayout {
    ProfileBL profileBL = ProfileBL.getProfileLogic();
    TextField name = new TextField("Name");
    TextField username = new TextField("Username");
    TextField email = new TextField("Email");
    TextField role = new TextField("Role");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    FormLayout formLayout = new FormLayout();
    private User user;

    public UserForm() {
        getStyle().set("margin-left", "15px");
        formLayout.add(
                name,
                username,
                email,
                role,
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

    public String getRole() {
        return role.getValue();
    }

    public void setRole(String role) {
        this.role.setValue(role);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> deleteUser());
        cancel.addClickListener(event -> this.setVisible(false));
        return new HorizontalLayout(save, delete, cancel);
    }

    private void deleteUser() {
        if (profileBL.deleteUser(user)) {
            Notification notification = new Notification("User sters cu succes!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        } else {
            Notification notification = new Notification("O eroare a intervenit la stergerea userului!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }

    private void validateAndSave() {
        user.setName(getName());
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setRole(getRole());
        if (profileBL.modifyUser(user)) {
            Notification notification = new Notification("User modificat cu succes!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        } else {
            Notification notification = new Notification("O eroare a intervenit la modificarea userului!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        }
    }


}
