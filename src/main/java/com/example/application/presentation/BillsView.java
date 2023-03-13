package com.example.application.presentation;

import com.example.application.business_logic.BillBLL;
import com.example.application.model.Bill;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import java.util.List;
@Route("hello")
public class BillsView extends HorizontalLayout implements BeforeEnterObserver {

    List<Bill> bills;
    BillBLL billBLL = BillBLL.getBillLogic();
    String userID;
    Dialog dialog = new Dialog();
    public BillsView(){

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        userID = event.getRouteParameters().get("userID").orElse("haha");
        createTable(userID);
    }

    private void createTable(String userID) {
        VerticalLayout table = new VerticalLayout();
        bills = billBLL.findAllBills("7");
        table.add(new BillHeadView());
        table.getStyle().set("background-color", " #f0f0f5");
        table.getStyle().set("width", "1100px");
        if(!bills.isEmpty()){
            for (Bill b : bills) {


//                System.out.println(b.getNumeFurnizor() + " " + b.getSumaDatorata() + " " + b.getSumaTotala() + " " + b.getScadenta() + " " + b.getStatus());
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                Label numeFurnizor = new Label(b.getNumeFurnizor());
//                Label sumaDatorata = new Label(String.valueOf(b.getSumaDatorata()));
//                Label sumaTotala = new Label(String.valueOf(b.getSumaTotala()));
//                Label scadenta = new Label(format.format(b.getScadenta()));
//                Label status = new Label(b.getStatus());
//                //payDialog.getFooter().add(close);
//                Button pay = new Button("Plata");
//                pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//                pay.addClassNames(LumoUtility.AlignItems.BASELINE);
//                pay.setHeight("25px");
//                pay.getStyle().set("margin-top", "2px");
//                numeFurnizor.getStyle().set("font-weight", "bold");
//                sumaDatorata.getStyle().set("font-weight", "bold");
//                sumaTotala.getStyle().set("font-weight", "bold");
//                scadenta.getStyle().set("font-weight", "bold");
//                status.getStyle().set("font-weight", "bold");
//                sumaDatorata.setWidth("150px");
//                sumaTotala.setWidth("150px");
//                numeFurnizor.setWidth("150px");
//                scadenta.setWidth("150px");
//                status.setWidth("150px");
//                pay.setWidth("150px");
//                table.add(new HorizontalLayout(numeFurnizor, sumaDatorata, sumaTotala, scadenta, status, pay));
            }
        }else{
            Label empty = new Label("Momentan nu aveti nici o factura!");
            empty.getStyle().set("font-weight","bold");
            table.add(new HorizontalLayout(empty));
        }

        table.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        table.setAlignItems(FlexComponent.Alignment.CENTER);
        add(table);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);
    }
}
