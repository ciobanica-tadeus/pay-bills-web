package com.example.application.business_logic;

import com.example.application.DAO.ProfileDAO;
import com.example.application.model.User;

import java.sql.SQLException;
import java.util.List;

public class ProfileBL {
    private ProfileDAO profile;
    private static ProfileBL profileLogic = new ProfileBL();

    private ProfileBL() {
        profile = new ProfileDAO();
    }

    public String getEmailAddress(int id) {
        return profile.getEmailAddress(id);
    }

    public String getUsername(String id) {
        return profile.getUsername(id);
    }

    public String getType(int id) {
        return profile.getType(id);
    }

    public User getUser(String userID) {
        return profile.getUser(userID);
    }

    public boolean modifyUser(User u) {
        return profile.modifyUser(u);
    }

    public int getID(String email) {
        return profile.getID(email);
    }

    public static ProfileBL getProfileLogic() {
        return profileLogic;
    }

    public String getName(String userID) throws SQLException {
        return profile.getName(userID);
    }

    public List<User> getAllUsers() throws SQLException {
        return profile.getAllUsers();
    }

    public List<User> getUsersByName(String name) {
        return profile.getUsersByName(name);
    }

    public boolean deleteUser(User user) {
        return profile.deleteUser(user);

    }

    public boolean modifyYourAccount(User user) {
        return profile.modifyYourAccount(user);
    }
}
