package com.example.application.presentation;

import com.example.application.business_logic.LoginBLL;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("login")
@PageTitle("Login")
//@RouteAlias("")
@AnonymousAllowed
public class LogInView extends VerticalLayout {

    private final LoginForm login = new LoginForm();
    private boolean isAuthenticated = false;
    LoginBLL loginBll = LoginBLL.getLoginLogic();

    public LogInView() {
        getStyle().set("background", "url(https://iili.io/HcXJVtt.jpg)");
        getThemeList().set("blue", true);
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        H4 question = new H4("New to Billy Pay ?");
        question.addClassNames(LumoUtility.AlignSelf.BASELINE);
        horizontalLayout.add(question);

        Button register = new Button("Join us");
        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        register.getStyle().set("width", "150px");
        register.addClassNames(LumoUtility.AlignSelf.BASELINE);
        horizontalLayout.add(register);

        register.addClickListener(e -> {
            UI.getCurrent().navigate(RegisterView.class);
        });
        login.setForgotPasswordButtonVisible(false);

        login.addLoginListener(e -> {
            isAuthenticated = loginBll.authenticate(e.getUsername(), e.getPassword());
            if (isAuthenticated) {
                loginBll.send();
                int id = loginBll.getID(e.getUsername());
                String role = loginBll.getRole(String.valueOf(id));
                if (role.equalsIgnoreCase("user")) {
                    UI.getCurrent().navigate(HomePage.class, new RouteParameters("userID", String.valueOf(id)) );
                }  else if(role.equalsIgnoreCase("admin")){
                    UI.getCurrent().navigate(AdminHomeView.class, new RouteParameters("adminID", String.valueOf(id)));
                }
                else {
                    login.setError(true);
                }
            }

        });

        add(
                login,
                horizontalLayout
        );
    }


}
