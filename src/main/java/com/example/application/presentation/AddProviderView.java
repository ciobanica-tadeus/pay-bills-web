package com.example.application.presentation;

import com.example.application.business_logic.BillBLL;
import com.example.application.model.Provider;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;
import java.util.Optional;

public class AddProviderView extends VerticalLayout {
    Grid<Provider> grid = new Grid<>(Provider.class, false);
    Label description = new Label("Alegeti un furnizor din tabelul de mai jos ");
    Button addProvider = new Button("Adaugati furnizor");
    private String userID;
    private BillBLL billBLL = BillBLL.getBillLogic();
    private String ID;

    public AddProviderView(List<Provider> providers, String userID) {
        this.userID = userID;
        setSizeFull();
        grid.setItems(providers);
        configureGrid();
        addProvider.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        description.getStyle().set("font-weight", "bold");

        addProvider.addClickListener(event -> {
            billBLL.insertIntoFactura(ID,userID);
            Notification notification = new Notification("Furnizor adaugat cu succes");
            notification.open();
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setDuration(2000);
            notification.setPosition(com.vaadin.flow.component.notification.Notification.Position.BOTTOM_CENTER);
        });
        add(description,grid,addProvider);
    }

    private void configureGrid() {
        grid.addColumn(Provider::getProviderName).setHeader("Name");
        grid.addColumn(Provider::getAddress).setHeader("Address");
        grid.addColumn(Provider::getTypeProvider).setHeader("Type");
        grid.addColumn(Provider::getUnitPrice).setHeader("Unit price");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(selectionEvent -> {
            Optional<Provider> optionalUser = selectionEvent.getFirstSelectedItem();
            if (optionalUser.isPresent()) {
                ID = optionalUser.get().getID();
            }
        });
    }
}
