package Domain;

import Interfaces.IAccount;
import Interfaces.IWriter;

import java.time.Year;

public abstract class Account implements IAccount {
    private String email, password, firstName, lastName;
    private IWriter write = main.getWriter();

    public Account(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addCredit(String title, String fName, String lName, CreditType role) {
        String credit = fName + ":" + lName + ":" + role.name();
        return write.addCredit(title, credit);
    }

    public void createMovie(String title, String bio, Year launchYear) {
        String broadcast = title + ":" +  bio + ":" + launchYear.toString() + ":" + "Movie";
        write.createBroadcast(broadcast);
    }

    public void createLiveShow(String title, String bio, Year launchYear, String location) {
        String liveShow = title + ":" + bio + ":" + launchYear.toString() + ":" + location + ":" + "LiveShow";
        write.createBroadcast(liveShow);
    }

    public void createEpisode(String title, String bio, Year launchYear, String showName, int season, int episode) {
        Episode ep = new Episode(title, bio, launchYear, showName, season, episode);
        String episodeString = title + ":" + bio + ":" + launchYear.toString() + ":" + showName + ":" + season + ":" + episode + ":" + "Episode";
        write.createBroadcast(episodeString);
    }

    public String deleteCredit(String title, Credit credit) {
        String creditToDelete = credit.getfName() + ":" + credit.getlName() + ":" + credit.getRole();
        return main.getWriter().deleteCredit(title, creditToDelete);
    }

    public String deleteBroadcast(String title)  {
        return main.getWriter().deleteBroadcast(title);
    }


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}