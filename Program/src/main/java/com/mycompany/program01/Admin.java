package com.mycompany.program01;

import java.util.UUID;

public class Admin extends Account implements IAdmin {
    String userID;

    public Admin(String username, String password, String name, String email) {
        super(username, password, name, email);
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

    public void createUser(String name, String username, String password, String email, String userID) {
        //TODO
        User user = new User(name, username, password, email, userID);
        //saveUser to file

    }
    public void deleteUser(String userID) { //TODO implement UUID i stedet for String i userID
        //TODO
        //Delete user i fil system med det userID
    }

    public void editUser() {
        //TODO
        /*
        User user = getUser(); //Kommer fra filsystemet
        user = userPresentation() //Kommer fra præsentationslaget
        saveUser(); //Gem til filsystemet og erstat tidligere user.
         */
    }
}
