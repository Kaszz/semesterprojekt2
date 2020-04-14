package Domain;

import java.net.URL;
import java.time.Year;

public class DomainFacade {
    private static DomainFacade instance = new DomainFacade();
    private Admin admin;
    private User user;
    private Credit credit;
    private Episode episode;
    private LiveShow liveShow;
    private Movie movie;

    private DomainFacade() {
        //NOTHING
    }

    public static DomainFacade getInstance() {
        return instance;
    }

    public User createUser(String userID, String username, String password, String name, String email) {
        user = new User(userID, username, password, name, email);
        return user;
    }

    public Admin createAdmin(String username, String password, String name, String email) {
        admin = new Admin(username, password, name, email);
        return admin;
    }

    public Credit createCredit(String fName, String lName, CreditType role) {
        credit = new Credit(fName, lName, role);
        return credit;
    }

    public Episode createEpisode(String title, URL trailerURL, String bio, Year launchYear, String showName, int season, int episodeNum) {
        episode = new Episode(title, trailerURL, bio, launchYear, showName, season, episodeNum);
        return episode;
    }

    public LiveShow createLiveShow(String title, URL trailerURL, String bio, Year launchYear, String location) {
        liveShow = new LiveShow(title, trailerURL, bio, launchYear, location);
        return liveShow;
    }

    public Movie createMovie(String title, URL trailerURL, String bio, Year launchYear) {
        movie = new Movie(title, trailerURL, bio, launchYear);
        return movie;
    }
}
