package com.example.application.presentation;

import com.example.application.model.Bill;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AView extends HorizontalLayout {

    Dialog d = new Dialog();

    public AView(Bill b) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Label numeFurnizor = new Label(b.getNumeFurnizor());
        Label sumaDatorata = new Label(String.valueOf(b.getSumaDatorata()));
        Label sumaTotala = new Label(String.valueOf(b.getSumaTotala()));
        Label scadenta = new Label(format.format(b.getScadenta()));
        Label status = new Label(b.getStatus());
        //payDialog.getFooter().add(close);
        Button pay = new Button("Plata");

        d.add(new PayView(b));

        pay.addClickListener(l -> {
            d.open();

        });

        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        pay.addClassNames(LumoUtility.AlignItems.BASELINE);
        pay.setHeight("25px");
        pay.getStyle().set("margin-top", "2px");
        numeFurnizor.getStyle().set("font-weight", "bold");
        sumaDatorata.getStyle().set("font-weight", "bold");
        sumaTotala.getStyle().set("font-weight", "bold");
        scadenta.getStyle().set("font-weight", "bold");
        status.getStyle().set("font-weight", "bold");
        sumaDatorata.setWidth("150px");
        sumaTotala.setWidth("150px");
        numeFurnizor.setWidth("150px");
        scadenta.setWidth("150px");
        status.setWidth("150px");
        pay.setWidth("150px");
        add(numeFurnizor, sumaDatorata, sumaTotala, scadenta, status, pay);
    }
}
