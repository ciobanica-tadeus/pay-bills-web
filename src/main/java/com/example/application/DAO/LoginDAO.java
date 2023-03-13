package com.example.application.DAO;

import com.example.application.DataAccess.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    private String createSelectQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("user.id_user ");
        sb.append("FROM user ");
        sb.append("WHERE (user.username = ? AND user.password = ?)");
        return sb.toString();
    }

    private String createSelectIDQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("id_user ");
        sb.append("FROM user ");
        sb.append("WHERE username = ?;");
        return sb.toString();
    }

    private String createRoleQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("type FROM user WHERE id_user = ?;");
        return sb.toString();
    }

    public boolean isUser(String username,String password) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {

            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next())
                return true;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return false;
    }

    public int findID(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectIDQuery();
        try {

            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return -1;
    }

    public String findRole(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createRoleQuery();
        try {

            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return "";
    }
}
