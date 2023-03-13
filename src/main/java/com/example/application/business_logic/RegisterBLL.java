package com.example.application.business_logic;

import com.example.application.DAO.RegistrationDAO;

import java.sql.SQLException;

public class RegisterBLL {
    private RegistrationDAO registration;

    public RegisterBLL(){
        registration = new RegistrationDAO();
    }

    public boolean register(String name,String username, String password,String email) throws SQLException {
//        boolean result = registration.insert(name, username, password, email);
//        if ( result){
//            System.out.println("Register is ok");
//        } else {
//            System.out.println("Register failed");
//        }
        return registration.insert(name, username, password, email);
    }
}
