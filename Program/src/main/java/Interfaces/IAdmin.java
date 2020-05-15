package Interfaces;

import Domain.User;

import java.util.ArrayList;

public interface IAdmin extends IAccount {
    public User createUser(String email, String password, String firstName, String lastName);
    public void deleteUser(int userID);
    public void editUser(int userID, String email, String password, String fName, String lName);
    public ArrayList<String> getAllUsers();
}
