package Domain;

import Interfaces.IAdmin;
import Interfaces.IReader;
import Interfaces.IWriter;

import java.time.Year;
import java.util.ArrayList;
import java.util.UUID;

public class Admin extends Account implements IAdmin {
    int userID;
    private IReader read = main.getReader();
    private IWriter write = main.getWriter();

    public Admin(int userID, String email, String password, String firstName, String lastName) {
        super(userID, email, password, firstName, lastName);
    }


    public User createUser(String email, String password, String firstName, String lastName) {
        String userToCreate = email + ":" + password + ":" + firstName + ":" + lastName + ":" + "true";
        int id = write.createUser(userToCreate);
        User user = new User(id, password, firstName, lastName, email);

        return user;
    }

    //TODO implement UUID i stedet for String i userID
    public void deleteUser(int userID) {
        write.deleteUser(userID);
    }

    public void editUser(int userID, String email, String password, String firstName, String lastName, boolean enabled) {
        String userToEdit = userID + ":" + email + ":" + password + ":" + firstName + ":" + lastName + ":" + enabled;
        write.editUser(userToEdit);
    }

    @Override
    public boolean addCredit(String title, String fName, String lName, CreditType role) {
        return super.addCredit(title, fName, lName, role);
    }

    @Override
    public void createMovie(String title, String bio, Year launchYear, int userID) {
        super.createMovie(title, bio, launchYear, userID);
    }

    @Override
    public void createLiveShow(String title, String bio, Year launchYear, String location, int userID) {
        super.createLiveShow(title, bio, launchYear, location, userID);
    }

    @Override
    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode, int userID) {
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
