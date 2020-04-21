package Interfaces;

import java.util.ArrayList;

public interface IAdmin extends IAccount {
    public void createUser(String userID, String email, String password, String firstName, String lastName);
    public void deleteUser(String userID, String email, String password, String firstName, String lastName, boolean enabled);
    public void editUser(String userID, String email, String password, String firstName, String lastName, boolean enabled);
    public ArrayList<String> getAllUsers();
}
