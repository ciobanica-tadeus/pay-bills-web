package com.example.application.presentation;

import com.example.application.business_logic.ProfileBL;
import com.example.application.model.User;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@PageTitle("Admin page")
@Route(value = "admin/:adminID/")
public class AdminHomeView extends AppLayout implements BeforeEnterObserver {
    private String adminID;
    private String userEmail;
    private String userRole;

    private ProfileBL profileLogic = ProfileBL.getProfileLogic();
    private UserForm form;
    private HorizontalLayout main = new HorizontalLayout();
    Grid<User> userList = new Grid<>(User.class, false);

    TextField filterByName = new TextField();

    public AdminHomeView() {
        getStyle().set("background", "url(https://iili.io/HcXJVtt.jpg)");

    }

    private void createHeader(String adminID) throws SQLException {
        HorizontalLayout hLayout = new HorizontalLayout();
        // Configure styling for the header
        hLayout.getThemeList().set("dark", true);
        hLayout.setWidthFull();
        hLayout.setSpacing(false);
        //For the log out button
        Button logOutBtn = new Button("Log Out");
        logOutBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        logOutBtn.getStyle().set("margin-top", "10px");
        logOutBtn.getStyle().set("margin-right", "10px");
        //add listener to the log out button
        logOutBtn.addClickListener(event -> {
            UI.getCurrent().navigate(LogInView.class);
        });
        //FOR hello title
        H3 hello = new H3("Bine ati revenit, " + profileLogic.getName(adminID) + "!");
        hello.getStyle().set("font-family", "Georgia");
        hello.getStyle().set("font-size", "25px");
        hello.getStyle().set("font-weight", "bold");
        hello.getStyle().set("margin-top", "10px");
        hello.getStyle().set("margin-left", "10px");

        hLayout.add(hello, logOutBtn);
        hLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //hLayout.add(header);
        addToNavbar(hLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        adminID = event.getRouteParameters().get("adminID").orElse("haha");
        userEmail = profileLogic.getEmailAddress(Integer.parseInt(adminID));
        userRole = profileLogic.getType(Integer.parseInt(adminID));
        try {
            createHeader(adminID);
            createTableOfUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void configureForm() {
        form = new UserForm();
        form.setWidth("25em");
    }

    private void createTableOfUsers() throws SQLException {
        List<User> users = profileLogic.getAllUsers();
        //add grid
        main.add(new UserListView(users));

        configureForm();
        form.getStyle().set("background-color", "white");

        main.add(form);
        setContent(main);
    }

    public class UserListView extends VerticalLayout {

        Grid<User> userList = new Grid<>(User.class, false);
        TextField filterByName = new TextField();

        public UserListView(List<User> users) {
            setSizeFull();
            configureGrid();
            userList.setItems(users);
            add(getToolBar(), userList);

        }

        private void configureGrid() {
            userList.addColumn(User::getId).setHeader("ID");
            userList.addColumn(User::getName).setHeader("Name");
            userList.addColumn(User::getUsername).setHeader("Username");
            userList.addColumn(User::getEmail).setHeader("Email");
            userList.addColumn(User::getPassword).setHeader("Password");
            userList.addColumn(User::getRole).setHeader("Role");
            userList.getColumns().forEach(col -> col.setAutoWidth(true));
            userList.setSelectionMode(Grid.SelectionMode.SINGLE);
            userList.addSelectionListener(selectionEvent -> {
                form.setVisible(true);
                Optional<User> optionalUser = selectionEvent.getFirstSelectedItem();
                if (optionalUser.isPresent()) {
                    form.setName(optionalUser.get().getName());
                    form.setUsername(optionalUser.get().getUsername());
                    form.setEmail(optionalUser.get().getEmail());
                    form.setRole(optionalUser.get().getRole());
                    User formUser = new User(
                            optionalUser.get().getId(),
                            optionalUser.get().getName(),
                            optionalUser.get().getUsername(),
                            optionalUser.get().getPassword(),
                            optionalUser.get().getEmail(),
                            optionalUser.get().getRole()
                    );
                    form.setUser(formUser);
                }
            });

        }

        private HorizontalLayout getToolBar() {
            filterByName.setPlaceholder("Filter by name");
            filterByName.setClearButtonVisible(true);
            filterByName.setValueChangeMode(ValueChangeMode.LAZY);
            filterByName.addValueChangeListener(event -> {
                try {
                    updateList();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            return new HorizontalLayout(filterByName);
        }

        private void updateList() throws SQLException {
            userList.setItems(profileLogic.getUsersByName("%" + filterByName.getValue() + "%"));
        }
    }

}
