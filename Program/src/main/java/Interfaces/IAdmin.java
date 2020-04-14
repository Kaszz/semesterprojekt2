package Interfaces;

public interface IAdmin extends IAccount {
    public void createUser(String userID, String username, String password, String name, String email);
    public void deleteUser(String userID, String username, String password, String name, String email, boolean enabled);
    public void editUser(String userID, String username, String password, String name, String email, boolean enabled);
}
