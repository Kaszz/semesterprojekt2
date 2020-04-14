package Domain;

import Data.DataFacade;
import Interfaces.IReader;
import Interfaces.IWriter;

public class Login {
    static boolean loggedIn;
    static User tempUser;

    public static void login(String username, String password) {
        IReader read = main.getReader();
        String[] info;
        String returnString = read.checkUser(username, password);
        if (returnString.equals("User not found.")) {
            System.out.println(returnString);
        } else {
            loggedIn = true;
            info = returnString.split(":");
            tempUser = new User(info[0], info[1], info[2], info[3], info[4]);
        }
    }

    public static boolean isloggedIn() {
        return loggedIn;
    }

    public static User getUser() {
            return tempUser;
    }
}
