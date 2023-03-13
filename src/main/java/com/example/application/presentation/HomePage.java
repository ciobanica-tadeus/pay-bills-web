package com.example.application.presentation;

import com.example.application.business_logic.BillBLL;
import com.example.application.business_logic.ProfileBL;
import com.example.application.business_logic.ProviderBLL;
import com.example.application.model.Bill;
import com.example.application.model.Provider;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY_INLINE;


@PageTitle("Home")
@Route(value = "user/:userID/")
public class HomePage extends AppLayout implements Observer, BeforeEnterObserver {

    private String userID;
    private String userEmail;
    private String userRole;
    private Dialog addProviderDialog = new Dialog();
    private Dialog editProfileDialog = new Dialog();

    private ProfileBL profileLogic = ProfileBL.getProfileLogic();
    private BillBLL billBLL = BillBLL.getBillLogic();
    private ProviderBLL providerBLL = ProviderBLL.getBillLogic();

    private VerticalLayout main = new VerticalLayout();

    public HomePage() {
        getStyle().set("background", "url(https://iili.io/HcXJVtt.jpg)");
    }

    private static Notification createNotification() {
        Notification notification = new Notification();

        Span name = new Span();
        name.getStyle().set("font-weight", "500");
        Div info = new Div(
                name,
                new Text("Log in succeeded!")
        );

        HorizontalLayout layout = new HorizontalLayout(info, createCloseButton(notification));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);

        return notification;
    }

    private static Button createCloseButton(Notification notification) {
        Button closeButton = new Button(
                VaadinIcon.CLOSE_SMALL.create(),
                clickEvent -> notification.close());
        closeButton.addThemeVariants(LUMO_TERTIARY_INLINE);

        return closeButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        Notification notification = createNotification();
        notification.setDuration(2000);
        notification.open();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        userID = beforeEnterEvent.getRouteParameters().get("userID").orElse("haha");
        userEmail = profileLogic.getEmailAddress(Integer.parseInt(userID));
        userRole = profileLogic.getType(Integer.parseInt(userID));
        try {
            createHeader(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        configurePage(userID);
    }

    private void configurePage(String userID) {
        VerticalLayout table = new VerticalLayout();

        //Add furnizor button
        Button furnizorBtn = new Button("Adaugare furnizor nou");
        furnizorBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        List<Provider> providers = providerBLL.findAllProviders();
        addProviderDialog.add(new AddProviderView(providers, userID));
        addProviderDialog.setWidth("1000px");
        addProviderDialog.setHeight("800px");
        furnizorBtn.addClickListener(event -> {
            addProviderDialog.open();
        });

        //Add edit user details
        Button editUserBtn = new Button("Editare detalii cont");
        editUserBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button close = new Button("Cancel");
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        EditUserForm editUserForm = new EditUserForm();
        editUserForm.setUser(profileLogic.getUser(userID));
        editProfileDialog.add(editUserForm);
        editProfileDialog.add((new VerticalLayout(close)));
        close.addClickListener(event -> editProfileDialog.close());

        editUserBtn.addClickListener(event -> {
            editProfileDialog.open();
            editProfileDialog.setWidth("800px");
            editProfileDialog.setHeight("350px");
        });
        table.add(new HorizontalLayout(editUserBtn, furnizorBtn));
        table.add(new Label());
        table.add(new BillHeadView());
        table.getStyle().set("background-color", " #f0f0f5");
        table.getStyle().set("width", "1100px");
        List<Bill> bills = billBLL.findAllBills(userID);
        if (!bills.isEmpty()) {
            for (Bill b : bills) {
                AView bw = new AView(b);
                table.add(bw);
            }
        } else {
            Label empty = new Label("Momentan nu aveti nici o factura!");
            empty.getStyle().set("font-weight", "bold");
            table.add(new HorizontalLayout(empty));
        }

        table.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        table.setAlignItems(FlexComponent.Alignment.CENTER);
        main.add(table);
        main.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        main.setAlignItems(FlexComponent.Alignment.CENTER);
        setContent(main);
    }

    private void createHeader(String userID) throws SQLException {
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
        H3 hello = new H3("Bine ati revenit, " + profileLogic.getName(userID) + "!");
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


}
