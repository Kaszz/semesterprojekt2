package Domain;

import javafx.application.Platform;

public class Admin extends Account implements IAdmin {
    String userID;

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

    public void createUser(String userID, String email, String password, String firstName, String lastName, boolean enabled) {
        //TODO Should use User object instead from presentation
        User user = new User(userID, password, firstName, lastName, email);
        String userToCreate = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + enabled;
        main.getWriter().createUser(userToCreate);
    }

    public void deleteUser(String userID, String email, String password, String firstName, String lastName, boolean enabled) { //TODO implement UUID i stedet for String i userID
        //TODO Should use User object instead from presentation
        String userToDelete = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + enabled;
        main.getWriter().deleteUser(userToDelete);
    }

    public void editUser(String userID, String email, String password, String firstName, String lastName, boolean enabled) {
        //TODO Should use User object instead from presentation
        String userToEdit = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + enabled;
        main.getWriter().editUser(userToEdit);
    }



}
