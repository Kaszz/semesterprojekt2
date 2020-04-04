package Data;

import Interfaces.IWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Writer implements IWriter {

    /**
     * A method for writing strings to files.
     * @param fileName is the name of the file that is writen to.
     * @param text is the string which is written to the file.
     * @return true if successful, otherwise false.
     */
    private boolean write2file(String fileName, String text, boolean append) {
        //Created file object with correct name.
        File file = new File("./src/txtfiles/broadcasts/" + fileName + ".txt");

        //Checks if the file already exists.
        if (file.exists()) {
            //Creates FileWriter
            FileWriter writer = null;
            try {
                //Setup FileWriter to append to the file.
                writer = new FileWriter(file, append);
                //Writes to the file, makes a newline and closes the FileWriter.
                //writer.write("\r\n");
                writer.write(text);
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * Method for creating new broadcasts in the database.
     * @param broadcast is the full string containing relevant information about the broadcast.
     * @return true if successful, otherwise false.
     */
    @Override
    public boolean createBroadcast(String broadcast) {
        //Takes substring of the argument by seperating at first ':' to find the filename.
        //Ex. Fathers:http://www.google.com:They are old:2021
        //Extracts the string "Fathers" from the above example.
        String fileName = broadcast.substring(0, broadcast.indexOf(':'));
        File file = new File("./src/txtfiles/broadcasts/" + fileName + ".txt");

        //The file doesnt exist.
        if (!file.exists()) {
            //Creates the file
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Writes broadcast details to file
            write2file(fileName, broadcast, true);
            return true;
        }

        return false;
    }

    /**
     * A method for deleting broadcasts in the database.
     * @param title is the name of the broadcast.
     * @return returns a string explaining the outcome.
     */
    @Override
    public String deleteBroadcast(String title) {
        File file = new File("./src/txtfiles/broadcasts/" + title + ".txt");
        String returnString = null;

        if(file.exists())
        {
            if (file.delete())
                returnString = file.getName() + " was succesfully removed.";
            else
                returnString = file.getName() + " could not be removed.";
        }
        else
            returnString = file.getName() + " could not be found in the database.";

        return returnString;
    }

    /**
     * A method to write a credit to an already existing broadcast.
     * @param title is the name of the broadcast.
     * @param credit is the full string containing all information about the credit.
     * @return true if successful, otherwise false.
     */
    @Override
    public boolean addCredit(String title, String credit) {
        Reader read = new Reader();
        ArrayList<String> list = read.getBroadcastCredits(title);

        //Short string only containing first and last name of the credit argument.
        String creditName = credit.substring(0, credit.indexOf(":", credit.indexOf(":") + 1));

        //Iterate through the list of credits for the broadcast.
        for (String s: list) {
            //Makes a substring only containing the name of all the crediting.
            String listName = s.substring(0, s.indexOf(":", s.indexOf(":") + 1));
            //Compares the name from argument and the ones already in the broadcast.
            if (listName.equals(creditName))
                return false;
        }

        return write2file(title, "\r\n" + credit, true);
    }

    /**
     * Method to delete specific credit in specific broadcast.
     * @param title is the name of the broadcast.
     * @param credit is the full credit-line that is to be deleted.
     */
    @Override
    public String deleteCredit(String title, String credit) {
        File file = new File("./src/txtfiles/broadcasts/" + title + ".txt");
        String tempString = "";
        String returnString = null;

        //Checks if file exists.
        if (file.exists()) {
            try {
                //Opens scanner.
                Scanner scan = new Scanner(file);

                //Iterates through file line for line.
                while(scan.hasNextLine()) {
                    String line = scan.nextLine();
                    //Saves the correct credits into tempString.
                    if (!line.equals(credit)) {
                        tempString += line + "\r\n";
                    }

                }
                scan.close();

                //Overwrites the file.
                if (write2file(title, tempString, false))
                    returnString = "The credit was succesfully removed.";
                else
                    returnString = "Could not delete the credit.";

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                returnString = "The broadcast was not found in the database.";
                return returnString;
            }
        }
        else
            returnString = "The broadcast was not found in the database.";

        return returnString;
    }
}
