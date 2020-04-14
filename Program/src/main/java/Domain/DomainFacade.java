package Domain;

import Interfaces.IAccount;
import Interfaces.IAdmin;
import Interfaces.IBroadcast;

public class DomainFacade {
    private Account account;
    private Admin admin;
    private Broadcast broadcast;
    private Credit credit;
    private Episode episode;
    private LiveShow liveShow;
    private Movie movie;
    private User user;

    public DomainFacade() {

    }

    public User createUser(String userID, String username, String password, String name, String email) {
        user = new User(userID, username, password, name, email);
        return user;
    }

}
