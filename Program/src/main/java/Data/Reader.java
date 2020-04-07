package Data;

import Interfaces.IReader;

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
     * @param firstName persons first name.
     * @param lastName persons last name.
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
}