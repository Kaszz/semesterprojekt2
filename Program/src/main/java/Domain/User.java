package Domain;

import Interfaces.IUser;

public class User extends Account implements IUser {
    private String userID;
    private boolean enabled = true;

    public User(String userID, String username, String password, String name, String email) {
        super(username, password, name, email);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public boolean getEnabled() {
        return enabled;
    }
}
