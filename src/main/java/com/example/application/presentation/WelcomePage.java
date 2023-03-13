package com.example.application.presentation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Welcome")
@Route("")
@RouteAlias("")
public class WelcomePage extends VerticalLayout {

    public WelcomePage() {
        getStyle().set("background","url(https://iili.io/HcXJVtt.jpg)");
        setWidthFull();
        setHeightFull();
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("Billy Pay - All your bills in one place");
        title.getStyle().set("font-family", "cursive");
        title.getStyle().set("color", "#94b8b8");
        title.addClassNames(LumoUtility.AlignSelf.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button startBtn = new Button("Start", new Icon(VaadinIcon.ARROW_RIGHT));
        startBtn.setIconAfterText(true);
        startBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        startBtn.getStyle().set("width","300px");
        startBtn.getStyle().set("font-size","25px");
        horizontalLayout.addClassNames(LumoUtility.AlignSelf.CENTER);
        horizontalLayout.add(startBtn);
        add(title);
        add(horizontalLayout);

        startBtn.addClickListener(e -> {
            startBtn.getUI().ifPresent(ui -> ui.navigate("/login"));
        });
    }
}
