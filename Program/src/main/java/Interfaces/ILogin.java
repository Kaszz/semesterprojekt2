package Interfaces;

import Domain.User;

public interface ILogin {

    public void login(String email, String password);

    public boolean isloggedIn();

    public User getUser();

    public void logout();


}
