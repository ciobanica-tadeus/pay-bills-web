package com.example.application.business_logic;

import com.example.application.DAO.LoginDAO;
import com.example.application.presentation.HomePage;

import java.util.Observable;

public class LoginBLL extends Observable {
    private LoginDAO logIn;
    private static LoginBLL logInLogic = new LoginBLL();

    public LoginBLL() {
        this.logIn = new LoginDAO();
    }

    public boolean authenticate(String username, String password){
        return logIn.isUser(username,password);
    }

    public int getID(String username) {
        return logIn.findID(username);
    }

    public static LoginBLL getLoginLogic() {
        return logInLogic;
    }

    public void send() {
        setChanged();
        addObserver(new HomePage());
        notifyObservers();
    }

    public String getRole(String valueOf) {
        return logIn.findRole(valueOf);
    }
}
