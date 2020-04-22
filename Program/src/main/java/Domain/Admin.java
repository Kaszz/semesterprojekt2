package Domain;

import Interfaces.IAdmin;
import Interfaces.IReader;
import Interfaces.IWriter;

import java.util.ArrayList;

public class Admin extends Account implements IAdmin {
    String userID;
    private IReader read = main.getReader();
    private IWriter write = main.getWriter();

    public Admin(String userID, String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    public void changeUser(int userID, boolean state) { //TODO implement UUID i stedet for String i userID
        //TODO
        /*
        User user;
        user = getUser(userID);
        user.setEnabled(false);
        //saveUser to file
         */
    }

    public void createUser(String userID, String email, String password, String firstName, String lastName) {
        User user = new User(userID, password, firstName, lastName, email);
        String userToCreate = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + "true";
        write.createUser(userToCreate);
    }

    //TODO implement UUID i stedet for String i userID
    public void deleteUser(String userID, String email, String password, String firstName, String lastName, boolean enabled) {
        String userToDelete = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + enabled;
        write.deleteUser(userToDelete);
    }

    public void editUser(String userID, String email, String password, String firstName, String lastName, boolean enabled) {
        String userToEdit = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + enabled;
        write.editUser(userToEdit);
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> returnList = read.getAllUsers();
        return returnList;
    }
}
