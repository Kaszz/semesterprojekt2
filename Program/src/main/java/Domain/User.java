package Domain;

public class User extends Account implements IUser {
    private String userID;
    private boolean enabled = true;

    public User(String username, String password, String name, String email, String userID) {
        super(username, password, name, email);
        this.userID = userID;
    }
}
