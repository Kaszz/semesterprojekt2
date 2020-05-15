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

    @Override
    public void deleteUser(int userID) {
        write.deleteUser(userID);
    }

    @Override
    public void editUser(int userID, String email, String password, String fName, String lName) {
        write.editUser(userID, email, password, fName, lName);
    }

    @Override
    public void addCredit(int broadcastID, String title, String fName, String lName, CreditType role) {
        super.addCredit(broadcastID, title, fName, lName, role);
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
    public void editMovie(int broadcast_id, String title, String bio, int launchYear, String oldTitle) {
        super.editMovie(broadcast_id, title, bio, launchYear, oldTitle);
    }

    @Override
    public void editLiveShow(int broadcast_id, String title, String bio, int launchYear, String location, String oldTitle) {
        super.editLiveShow(broadcast_id, title, bio, launchYear, location, oldTitle);
    }

    @Override
    public void editEpisode(int broadcast_id, String title, String bio, int launchYear, int seaNum, int epiNum, String oldTitle) {
        super.editEpisode(broadcast_id, title, bio, launchYear, seaNum, epiNum, oldTitle);
    }

    @Override
    public void deleteCredit(Credit credit, String title) {
        super.deleteCredit(credit.getCreditID(), credit);
    }

    public void deleteBroadcast(int broadcast_id, String title) {write.deleteBroadcast(broadcast_id);};
    public void deleteEpisode(int episode_id, String title) {write.deleteEpisode(episode_id);};

    public ArrayList<String> getAllUsers() {
        ArrayList<String> returnList = read.getAllUsers();
        return returnList;
    }

}
