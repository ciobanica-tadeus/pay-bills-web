package com.example.application.DAO;

import com.example.application.DataAccess.ConnectionFactory;
import com.example.application.model.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProviderDAO {

    public String createSelectAllProviders(){
        StringBuilder sb = new StringBuilder();
        sb.append("Select * from furnizor ");
        return sb.toString();
    }

    public List<Provider> findAllProviders() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllProviders();
        List<Provider> providers = new ArrayList<>();
        try {

            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                String ID = resultSet.getString(1);
                String nume = resultSet.getString(2);
                String address = resultSet.getString(3);
                String tipFurnizor =resultSet.getString(4);
                float unitPrice = resultSet.getFloat(5);
//                System.out.println(scadenta.toString());
//                System.out.println(String.valueOf(suma_datorata));
//                System.out.println(String.valueOf(suma_totala));
//                System.out.println(status);
                Provider provider = new Provider(ID,nume,address,tipFurnizor,unitPrice);
                providers.add(provider);
            }
            return providers;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

}
