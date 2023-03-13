package com.example.application.DAO;

import com.example.application.DataAccess.ConnectionFactory;
import com.example.application.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BillDAO {

    private String createSelectAllBillsQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT distinct ");
        sb.append("factura.id_factura, furnizor.nume, factura.suma_datorata, factura.suma_totala, ");
        sb.append("factura.scadenta, factura.status FROM ");
        sb.append("furnizor inner join factura join user ");
        sb.append("WHERE (furnizor.id_furnizor = factura.id_furnizor) and (factura.id_user = ?);");
        return sb.toString();
    }

    public List<Bill> findAllBills(String userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllBillsQuery();
        List<Bill> bills = new ArrayList<>();
        try {

            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id_factura = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                float suma_datorata = resultSet.getFloat(3);
                float suma_totala = resultSet.getFloat(4);
                Date scadenta = resultSet.getDate("scadenta");
                String status = resultSet.getString(6);
//                System.out.println(scadenta.toString());
//                System.out.println(String.valueOf(suma_datorata));
//                System.out.println(String.valueOf(suma_totala));
//                System.out.println(status);
                Bill bill = new Bill(id_factura, suma_totala, suma_datorata, status, nume, scadenta);
                bills.add(bill);
            }
            return bills;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }


    private String updateStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE factura ");
        sb.append("SET status=\"Achitat\" ");
        sb.append("WHERE id_factura = ?;");
        return sb.toString();
    }

    private String updateSumaDatorata() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE factura ");
        sb.append("SET suma_datorata = 0.0 ");
        sb.append("WHERE id_factura = ?;");
        return sb.toString();
    }

    private String insertIntoFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into factura(id_user, id_furnizor, suma_totala,suma_datorata, scadenta, status) ");
        sb.append("values (?, ?, ?, ?, ?, \"Neachitat\" );");
        return sb.toString();
    }

    public boolean updateBillStatus(Bill b) {
        Connection con = null;
        PreparedStatement stmt = null;
        String query = updateStatus();

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, b.getId_factura());
            stmt.executeUpdate();

            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(stmt);
            ConnectionFactory.close(con);
        }

        return false;
    }

    public void updateSumaDatorata(Bill b) {
        Connection con = null;
        PreparedStatement stmt = null;
        String query = updateSumaDatorata();

        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, b.getId_factura());
            stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(stmt);
            ConnectionFactory.close(con);
        }
    }

    public void insertIntoFactura(String providerID, String userID) {
        Connection con = null;
        PreparedStatement stmt = null;
        String query = insertIntoFactura();

        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            Random rand = new Random();
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1,userID);
            stmt.setString(2, providerID);

            float randomisedTotalValue = rand.nextFloat();
            randomisedTotalValue *= 100.0;
            float randomisedDatorat = rand.nextFloat();
            randomisedDatorat = (randomisedDatorat * 10.0f);

            stmt.setString(3,Float.toString(Math.round(randomisedTotalValue * 1000.0f) /100.0f) );
            stmt.setString(4,Float.toString(Math.round(randomisedDatorat * 1000.0f) /100.0f));
            stmt.setDate(5, java.sql.Date.valueOf("2023-05-12"));
            stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.close(stmt);
            ConnectionFactory.close(con);
        }
    }

}
