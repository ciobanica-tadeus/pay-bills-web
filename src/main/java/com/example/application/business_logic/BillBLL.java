package com.example.application.business_logic;

import com.example.application.DAO.BillDAO;
import com.example.application.model.Bill;

import java.util.List;

public class BillBLL {
    private BillDAO billDAO;
    private static BillBLL billLogic = new BillBLL();

    public BillBLL(){
        billDAO = new BillDAO();
    }

    public static BillBLL getBillLogic(){
        return billLogic;
    }

    public List<Bill> findAllBills(String userID){
        return billDAO.findAllBills(userID);
    }

    public boolean updateBillStatus(Bill b) {
        return billDAO.updateBillStatus(b);
    }

    public void updateSumaDatorata(Bill b) {
        billDAO.updateSumaDatorata(b);
    }
    public void insertIntoFactura(String providerID, String userID){
        billDAO.insertIntoFactura(providerID, userID);
    }
}
