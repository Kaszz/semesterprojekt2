package Domain;

import Interfaces.ILogin;
import Interfaces.IReader;


public class Login implements ILogin {
    private static Login instance = new Login();
    static boolean loggedIn;
    static Account account;

    private Login() {
        //Does nothing.
    }

    public void login(String email, String password) {
        IReader read = main.getReader();
        String[] info;
        String returnString = read.checkUser(email, password);

        if (returnString.equals("User not found.")) {
            System.out.println(returnString);
        } else {
            loggedIn = true;
            info = returnString.split(":");

            if (info[0].equals("0"))
                account = new Admin(info[0], info[1], info[2], info[3], info[4]);
            else
                account = new User(info[0], info[1], info[2], info[3], info[4]);

        }
    }

    public boolean isloggedIn() {
        return loggedIn;
    }

    public Account getAccount() {
        if (isAdmin()) {
            account = (Admin) account;
            System.out.println(account.getFirstName());
        }

        return account;
    }

    public static Login getInstance() {
        return instance;
    }

    public boolean isAdmin() {
        if (account.getEmail().equals("admin")) //TODO make this work only for userID, not email.
            return true;
        return false;
    }

    public void logout() {
        loggedIn = false;
    }

}
