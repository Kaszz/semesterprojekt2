package Domain;

import javafx.application.Platform;

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

    public void createUser(String userID, String username, String password, String name, String email) {
        //TODO Should use User object instead from presentation
        User user = new User(userID, username, password, name, email);
        String userToCreate = userID + ":" + username + ":" + password + ":" + name + ":" + email + ":" + "true";
        main.getWriter().createUser(userToCreate);
    }

    public void deleteUser(String userID, String username, String password, String name, String email, boolean enabled) { //TODO implement UUID i stedet for String i userID
        //TODO Should use User object instead from presentation
        String userToDelete = userID + ":" + username + ":" + password + ":" + name + ":" + email + ":" + enabled;
        main.getWriter().deleteUser(userToDelete);
    }

    public void editUser(String userID, String username, String password, String name, String email, boolean enabled) {
        //TODO Should use User object instead from presentation
        String userToEdit = userID + ":" + username + ":" + password + ":" + name + ":" + email + ":" + enabled;
        main.getWriter().editUser(userToEdit);
    }



}
