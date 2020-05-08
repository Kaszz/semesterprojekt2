package Interfaces;

import Domain.Account;

public interface ILogin {

    public void login(String email, String password);

    public boolean isloggedIn();

    public Account getAccount();

    public boolean isAdmin();

    public void logout();

}
