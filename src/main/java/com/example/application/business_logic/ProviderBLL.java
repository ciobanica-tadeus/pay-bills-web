package com.example.application.business_logic;

import com.example.application.DAO.ProviderDAO;
import com.example.application.model.Provider;

import java.util.List;

public class ProviderBLL {
    private ProviderDAO providerDAO;
    private static ProviderBLL providerBLL = new ProviderBLL();

    public ProviderBLL() {
        this.providerDAO = new ProviderDAO();
    }

    public static ProviderBLL getBillLogic(){
        return providerBLL;
    }

    public List<Provider> findAllProviders(){
        return providerDAO.findAllProviders();
    }
}
