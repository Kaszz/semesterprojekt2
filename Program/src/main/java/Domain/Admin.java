package Domain;

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

    public void createUser(String username, String password, String name, String email, String userID) {
        //TODO
        User user = new User(username, password, name, email, userID);
        String userToCreate = userID + ":" +username + ":" + password + ":" + name + ":" + email ;
        super.write.createUser(userToCreate);
    }

    public void deleteUser(String username, String password, String name, String email, String userID) { //TODO implement UUID i stedet for String i userID
        //TODO Should use User object instead from presentation
        String userToDelete = userID + ":" + username + ":" + password + ":" + name + ":" + email;
        super.write.deleteUser(userToDelete);
    }

    public void editUser(String userID, String username, String password, String name, String email) {
        //TODO Should use User object instead from presentation
        String userToEdit = userID + ":" + username + ":" + password + ":" + name + ":" + email;
        super.write.editUser(userToEdit);
    }
}
