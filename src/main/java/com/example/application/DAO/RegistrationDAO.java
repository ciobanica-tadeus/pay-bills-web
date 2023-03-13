package com.example.application.DAO;

import com.example.application.DataAccess.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAO {

    private String createInsertQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("billing_system_db.user ");
        sb.append("(name,username,password,email,type");
        sb.append(") VALUES (");
        sb.append("?,");
        sb.append("?,");
        sb.append("?,");
        sb.append(" ?, ");
        sb.append("?);");
        return sb.toString();
    }

    private String createSelectQuery(){
        return "SELECT LAST_INSERT_ID();";
    }


    public boolean insert(String name,String username, String password,String email) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String query = createInsertQuery();
        PreparedStatement insertStatementUser = null;
        ResultSet resultSet = null;

        try {
            insertStatementUser = connection.prepareStatement(query);
            insertStatementUser.setString(1,name);
            insertStatementUser.setString(2,username);
            insertStatementUser.setString(3,password);
            insertStatementUser.setString(4,email);
            insertStatementUser.setString(5,"user");
            int result = insertStatementUser.executeUpdate();

            if(result > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(insertStatementUser);
            ConnectionFactory.close(connection);
        }

        return false;
    }
}
