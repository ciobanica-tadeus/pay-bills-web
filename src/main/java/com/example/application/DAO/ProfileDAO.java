package com.example.application.DAO;

import com.example.application.DataAccess.ConnectionFactory;
import com.example.application.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {

    private String createSelectNameQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("name ");
        sb.append("FROM user ");
        sb.append("WHERE id_user = ?;");
        return sb.toString();
    }

    private String createSelectEmailQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("email ");
        sb.append("FROM user ");
        sb.append("WHERE id_user = ?;");
        return sb.toString();
    }

    private String createSelectIDQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("id_user ");
        sb.append("FROM user ");
        sb.append("WHERE username = ?;");
        return sb.toString();
    }

    private String createSelectTypeQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("type ");
        sb.append("FROM user ");
        sb.append("WHERE id_user = ?;");
        return sb.toString();
    }

    private String createGetNameQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT name from user where id_user = ?;");
        return sb.toString();
    }

    public String createGetAllUsersQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from user ");
        return sb.toString();
    }

    public String createGetAllUsersByNameQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from user ");
        sb.append("WHERE user.name like ?; ");
        return sb.toString();
    }

    public String createUpdateUserQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE user ");
        sb.append("SET name = ?, ");
        sb.append("username = ?, ");
        sb.append("email = ?, ");
        sb.append("type = ? ");
        sb.append("WHERE id_user = ?; ");
        return sb.toString();
    }

    public String createUpdateYourAccountQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE user ");
        sb.append("SET name = ?, ");
        sb.append("username = ?, ");
        sb.append("email = ?, ");
        sb.append("password = ? ");
        sb.append("WHERE id_user = ?; ");
        return sb.toString();
    }

    public String createDeleteUserQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE from user WHERE id_user = ?;");
        return sb.toString();
    }

    public String getName(String userID) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createGetNameQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            ConnectionFactory.close(resultSet);
        }

        return " ";
    }

    public String getEmailAddress(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectEmailQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    public String getType(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectTypeQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    public int getID(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectIDQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return -1;
    }

    public String getUsername(String userID) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectNameQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return "";
    }

    public boolean modifyUser(User user) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        String query = createUpdateUserQuery();
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            updateStatement = connection.prepareStatement(query);
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getUsername());
            updateStatement.setString(3, user.getEmail());
            updateStatement.setString(4, user.getRole());
            updateStatement.setString(5, user.getId());
            System.out.println(updateStatement.toString());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("Select * from user where id_user = ?; ");
        return sb.toString();
    }

    public User getUser(String userID) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        String query = getUserQuery();
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            updateStatement = connection.prepareStatement(query);
            updateStatement.setString(1, userID);

            rs = updateStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);

                return new User (userID,name,username,password,email,"user");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createGetAllUsersQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                String id = resultSet.getString("id_user");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("type");
                User newUser = new User(id, name, username, password, email, role);
                users.add(newUser);
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<User> getUsersByName(String searchName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createGetAllUsersByNameQuery();

        try {
            connection = (Connection) ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, searchName);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                String id = resultSet.getString("id_user");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String role = resultSet.getString("type");
                User newUser = new User(id, name, username, password, email, role);
                users.add(newUser);
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public boolean deleteUser(User user) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        String query = createDeleteUserQuery();
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            deleteStatement = connection.prepareStatement(query);
            deleteStatement.setString(1, user.getId());
            deleteStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifyYourAccount(User user) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        String query = createUpdateYourAccountQuery();
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            updateStatement = connection.prepareStatement(query);
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getUsername());
            updateStatement.setString(3, user.getEmail());
            updateStatement.setString(4, user.getPassword());
            updateStatement.setString(5,user.getId());
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
