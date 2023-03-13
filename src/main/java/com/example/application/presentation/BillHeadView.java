package com.example.application.presentation;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class BillHeadView extends HorizontalLayout {

    public BillHeadView() {

        Label numeFurnizor = new Label("Nume furnizor");
        Label sumaDatorata = new Label("Suma datorata");
        Label sumaTotala = new Label("Suma totala");
        Label scadenta = new Label("Scadenta");
        Label status = new Label("Status");
        Label plata = new Label("");

        add(numeFurnizor, sumaDatorata, sumaTotala, scadenta, status, plata);
        sumaDatorata.setWidth("150px");
        sumaTotala.setWidth("150px");
        numeFurnizor.setWidth("150px");
        scadenta.setWidth("150px");
        status.setWidth("150px");
        plata.setWidth("150px");

        numeFurnizor.getStyle().set("font-weight", "bold");
        sumaDatorata.getStyle().set("font-weight", "bold");
        sumaTotala.getStyle().set("font-weight", "bold");
        scadenta.getStyle().set("font-weight", "bold");
        status.getStyle().set("font-weight", "bold");

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);


    }
}
