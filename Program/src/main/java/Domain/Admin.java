package Domain;

import Interfaces.IAdmin;
import Interfaces.IReader;
import Interfaces.IWriter;

import java.time.Year;
import java.util.ArrayList;
import java.util.UUID;

public class Admin extends Account implements IAdmin {
    String userID;
    private IReader read = main.getReader();
    private IWriter write = main.getWriter();

    public Admin(String userID, String email, String password, String firstName, String lastName) {
        super(userID, email, password, firstName, lastName);
    }


    public User createUser(String email, String password, String firstName, String lastName) {
        UUID id = UUID.randomUUID();
        User user = new User(id.toString(), password, firstName, lastName, email);
        String userToCreate = id.toString() + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + "true";
        write.createUser(userToCreate);
        return user;
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

    @Override
    public boolean addCredit(String title, String fName, String lName, CreditType role) {
        return super.addCredit(title, fName, lName, role);
    }


    @Override
    public void createMovie(String title, String bio, Year launchYear, String userID) {
        super.createMovie(title, bio, launchYear, userID);
    }

    @Override
    public void createLiveShow(String title, String bio, Year launchYear, String location, String userID) {
        super.createLiveShow(title, bio, launchYear, location, userID);
    }

    @Override
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, String userID) {
        super.createEpisode(title, bio, launchYear, showName, season, episode, userID);
    }

    @Override
    public String deleteCredit(String title, Credit credit) {
        return super.deleteCredit(title, credit);
    }

    public String deleteBroadcast(String title)  {
        return super.deleteBroadcast(title);
    }


    public ArrayList<String> getAllUsers() {
        ArrayList<String> returnList = read.getAllUsers();
        return returnList;
    }

    public ArrayList<String> getAllPrograms() {
        ArrayList<String> returnList = read.getAllUsers();
        return returnList;
    }
}
