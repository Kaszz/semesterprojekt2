package Data;

import Interfaces.IWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Writer implements IWriter {
        String broadcastDirectory = "broadcasts";
        String userDirectory = "users";
        String userFile = "users";
    /**
     * A method for writing strings to files.
     * @param fileName is the name of the file that is written to.
     * @param text is the string which is written to the file.
     * @return true if successful, otherwise false.
     */
    private boolean write2file(String fileName, String text, String directory, boolean append) {
        //Created file object with correct name.
        File file = new File("./src/txtfiles/" + directory + "/" + fileName + ".txt");

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
        //Takes substring of the argument by seperating at first ':' to find the filename. Last element is the type of Broadcast
        //Ex. Fathers:http://www.google.com:They are old:2021:Movie
        //Extracts the string "Fathers" from the above example.
        String fileName = broadcast.substring(0, broadcast.indexOf(':'));
        System.out.println(fileName);
        File file = new File("./src/txtfiles/" + broadcastDirectory + "/" + fileName + ".txt");

        //The file doesnt exist.
        if (!file.exists()) {
            //Creates the file
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Writes broadcast details to file
            write2file(fileName, broadcast, broadcastDirectory ,true);
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
        File file = new File("./src/txtfiles/"+ broadcastDirectory +"/" + title + ".txt");
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

        return write2file(title, "\r\n" + credit, broadcastDirectory , true);
    }

    /**
     * Method to delete specific credit in specific broadcast.
     * @param title is the name of the broadcast.
     * @param credit is the full credit-line that is to be deleted.
     */
    @Override
    public String deleteCredit(String title, String credit) {
        File file = new File("./src/txtfiles/"+ broadcastDirectory +"/" + title + ".txt");
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
                if (write2file(title, tempString,"broadcasts" ,false))
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

    /**
     * A method to add new users to a text file. Creates a new file if none is found
     * @param user String with the attributtes of a User object. Colon separated.
     * @return true if success, false if file not found
     */
    public boolean createUser(String user) {
        File file = new File("./src/txtfiles/"+ userDirectory+"/" + userFile + ".txt");

        //Checks if file already exists. If it doesn't it creates it.
        if (!file.exists()) {
            try {
                file.createNewFile();
                write2file(userFile, user, userDirectory, false);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } else {
            //If the folder is empty a new line is not made. If it's not a new line is made.
            if (file.length() == 0) {
                write2file(userFile, user, userDirectory, true);
                return true;
            } else {
                write2file(userFile, "\r\n" + user, userDirectory, true);
                return true;
            }

        }
    }

    /**
     * A method to delete users from a text file.
     * @param user String with the attributtes of a User object. Colon separated.
     * @return true if it's a success. false if the found couldn't be found or the file doesn't exist
     */
    public boolean deleteUser(String user) {
        File file = new File("./src/txtfiles/"+ userDirectory+"/" + userFile + ".txt");
        Scanner userTxt;
        String newText = "";

        //If the file exists code is run, if not the method exits returning a false
        if (file.exists()) {
            try {
                userTxt = new Scanner(file);
                boolean first = false;

                //Looks for the identical line to delete. Once found it's not added to the new text.
                while(userTxt.hasNextLine()) {
                    String line = userTxt.nextLine();
                    if (!line.equals(user)) {
                        if (first) {
                            newText = newText + "\r\n" + line;
                        }
                        else {
                            newText += line;
                            first = true; //The first line cannot have a new line. This is only done once.
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            write2file(userFile, newText, userDirectory, false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method to replace one user with another. They must have the same userID
     * @param user String with the attributtes of a User object. Colon separated.
     * @return true if a success. False if the file was not found or doesn't exist
     */
    public boolean editUser(String user) {
        File file = new File("./src/txtfiles/"+ userDirectory+"/" + userFile + ".txt");
        Scanner userTxt;
        String newText = "";

        //Takes the string from the first start of the string to the first ':'
        String userID = user.substring(0, user.indexOf(':'));

        //If the file exists code is run, if not the method exits returning a false
        if (file.exists()) {
            try {
                userTxt = new Scanner(file);
                boolean first = false;

                //Looks through the textfile until it finds a line with the same userID
                //Once it's find it saves the given user String instead of the line thusly replacing the old line.
                while(userTxt.hasNextLine()) {
                    String line = userTxt.nextLine();
                    if (!line.substring(0, line.indexOf(':')).equals(userID)) {
                        if (first) {
                            newText = newText + "\r\n" + line;
                        }
                        else {
                            newText += line;
                            first = true; //The first line cannot have a new line. This is only done once.
                        }
                    }
                    else {
                        if (first) {
                            newText = newText + "\r\n" + user;
                        }
                        else {
                            newText += user;
                            first = true; //The first line cannot have a new line. This is only done once.
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            write2file(userFile, newText, userDirectory, false);
            return true;
        } else {
            return false;
        }
    }
}
