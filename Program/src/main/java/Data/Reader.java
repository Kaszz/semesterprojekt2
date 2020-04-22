package Data;

import Interfaces.IReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader implements IReader {

    /**
     * Method to get broadcast detail.
     * @param title is the title of the broadcast which is to be read.
     * @return the string that describes a broadcast object from the file.
     */
    @Override
    public String getBroadcast(String title) {
        //Defines file and scanner.
        File file = new File("./src/txtfiles/broadcasts/" + title + ".txt");
        Scanner scan = null;
        String returnString;

        try {
            //Returns first line in scanner.
            scan = new Scanner(file);
            returnString = scan.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            returnString = "File was not found.";
        } finally {
            scan.close();
        }

        return returnString;
    }

    /**
     * Method to get all the credits of a given broadcast.
     * @param title is the title of the broadcast.
     * @return the list of credits in the broadcast.
     */
    @Override
    public ArrayList<String> getBroadcastCredits(String title) {
        File file = new File("./src/txtfiles/broadcasts/" + title + ".txt");
        Scanner scan = null;
        ArrayList<String> returnList = new ArrayList<String>();

        try {
            scan = new Scanner(file);
            //Skip first line.
            scan.nextLine();

            //Add credits to the list.
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                returnList.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }

        return returnList;
    }

    /**
     * A method that allows you to find all the credits for a person in all the broadcasts in the database.
     * @return a list of all credits to the specified person.
     */
    @Override
    public ArrayList<String> getPersonalCredits(String firstName, String lastName) {
        File directory = new File("./src/txtfiles/broadcasts/");
        //Makes array of files in directory.
        File[] files = directory.listFiles();
        Scanner scan = null;
        ArrayList<String> returnList = new ArrayList<String>();

        //Iterate through the files in the directory.
        for (File f: files) {
            try {
                scan = new Scanner(f);
                //Get the title name of current file.
                String firstLine = scan.nextLine();
                String title = firstLine.substring(0, firstLine.indexOf(":"));

                while(scan.hasNextLine()) {
                    String line = scan.nextLine();
                    //Extracts the first and last name from the full line.
                    String lineName = line.substring(0, line.indexOf(":", line.indexOf(":") + 1));

                    //Checks if a matching name is in the file.
                    if (lineName.equals(firstName + ":" + lastName)) {
                        String returnString = title + ":" + line;
                        returnList.add(returnString);
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }
        }

        return returnList;
    }



    public ArrayList<String> getAllUsers(){
        File file = new File("./src/txtfiles/users/" + "users.txt");
        Scanner scan = null;
        ArrayList<String> returnList = new ArrayList<String>();

        try {
            scan = new Scanner(file);
            //Add notifications to the list.
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                returnList.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }

        return returnList;
    }

    public ArrayList<String> getAllBroadcasts() {
        File file = new File("./src/txtfiles/broadcasts/");
        File[] filePath = file.listFiles();
        ArrayList<String> broadcasts = new ArrayList<>();
        String broadcast;

        Scanner scan = null;

        for (int i = 0; i < filePath.length; i++) {
            file = new File(filePath[i].getPath());

            try {
                scan = new Scanner(file);
                //Get the title name of current file.
                broadcast = scan.nextLine();
                broadcasts.add(broadcast);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }
        }
        return broadcasts;
    }


    /**
     * Method that checks if a user exists in the database.
     * @param emailAddress is the username of the user.
     * @param password is the password of the user.
     * @return a boolean statement depending on existance of user with correct params in database.
     */
    @Override
    public String checkUser(String emailAddress, String password) {
        File file = new File("./src/txtfiles/users/users.txt");
        Scanner scan = null;

        try {
            scan = new Scanner(file);

            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                int firstSeparator = line.indexOf(':') + 1;
                int secondSeparator = line.indexOf(':', firstSeparator);
                String email = line.substring(firstSeparator, line.indexOf(':', secondSeparator));
                String pword = line.substring(secondSeparator + 1, line.indexOf(':', secondSeparator + 1));

                if (email.equals(emailAddress)) {
                    if (pword.equals(password)) {
                        return line;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }

        return "User not found.";
    }

    @Override
    public synchronized ArrayList<String> getNotifications() {
        File file = new File("./src/txtfiles/notifications/" + "notifications.txt");
        Scanner scan = null;
        ArrayList<String> returnList = new ArrayList<String>();

        try {
            scan = new Scanner(file);
            scan.nextLine();
            //Add notifications to the list.
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                returnList.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }

        return returnList;
    }


}
