package Domain;

import Interfaces.ILogin;
import Interfaces.IReader;


public class Login implements ILogin {
    private static Login instance = new Login();
    static boolean loggedIn;
    static User tempUser;

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
            tempUser = new User(info[0], info[1], info[2], info[3], info[4]);
        }
    }

    public boolean isloggedIn() {
        return loggedIn;
    }

    public User getUser() {
        return tempUser;
    }

    public static Login getInstance() {
        return instance;
    }

    public void logout() {
        loggedIn = false;
    }

}
