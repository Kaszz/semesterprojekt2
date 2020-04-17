package Domain;

import Interfaces.IUser;

public class User extends Account implements IUser {
    private String userID;
    private boolean enabled = true;

    public User(String userID, String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public boolean getEnabled() {
        return enabled;
    }
}
