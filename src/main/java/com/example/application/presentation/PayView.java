package com.example.application.presentation;

import com.example.application.business_logic.BillBLL;
import com.example.application.model.Bill;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Route("payment")
public class PayView extends VerticalLayout {
    private BillBLL billBLL = BillBLL.getBillLogic();

    public PayView(Bill b) {
        H1 title = new H1("Detalii plată");
        title.getStyle().set("margin-top", "10px");
        VerticalLayout main = new VerticalLayout();

        TextField name = new TextField("Nume detinator");
        name.setHelperText("Format: Pop Ion");
        name.getStyle().set("margin-top", "0px");
        name.setWidth("350px");

        TextField nrCard = new TextField("Numar card");
        nrCard.setMinLength(16);
        nrCard.setValue("XXXX-XXXX-XXXX-XXXX");
        nrCard.getStyle().set("margin-top", "0px");
        nrCard.setWidth("350px");

        DatePicker expirationDate = new DatePicker("Data expirare");
        expirationDate.setValue(LocalDate.from(LocalDateTime.now()));
        nrCard.getStyle().set("margin-top", "0px");
        expirationDate.setWidth("350px");

        TextField cvv = new TextField("CVV");
        cvv.setValue("xxx");
        cvv.setWidth("350px");
        cvv.getStyle().set("margin-top", "0px");

        Button pay = new Button("Plateste");

        pay.addClickListener(l -> {
            billBLL.updateBillStatus(b);
            billBLL.updateSumaDatorata(b);
            Notification notification = new Notification("Factura s-a platit cu succes!");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setDuration(2000);
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
        });

        pay.setWidth("200px");
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        main.add(name, nrCard, expirationDate, cvv, pay);

        main.setJustifyContentMode(JustifyContentMode.CENTER);
        main.setAlignItems(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(title, main);
    }
}
