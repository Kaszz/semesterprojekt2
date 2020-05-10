package Data;

import Interfaces.IWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Writer implements IWriter {
       String broadcastDirectory = "broadcasts";
       String userDirectory = "users";
       String userFile = "users";
       Connection connection = ConnectionDB.getConnection();



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


    @Override
    public boolean createBroadcast(String broadcast) {
        String[] info = broadcast.split(":");

        //Writes info to broadcasts table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "broadcasts " +
                            "(title, bio, launchyear, account_id, type) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)"
            );
            insertStatement.setString(1, info[0]);                  //title
            insertStatement.setString(2, info[1]);                  //bio
            insertStatement.setInt(3, Integer.parseInt(info[2]));   //launchyear
            insertStatement.setInt(4, Integer.parseInt(info[3]));   //account_id
            insertStatement.setString(5, info[info.length-1]);      //type
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Gets broadcastID
        int broadcastID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT broadcast_id " +
                    "FROM broadcasts " +
                    "WHERE title = ? " +
                    "AND bio = ? " +
                    "AND launchyear = ?" +
                    "AND account_id = ?");
            queryStatement.setString(1, info[0]);
            queryStatement.setString(2, info[1]);
            queryStatement.setInt(3, Integer.parseInt(info[2]));
            queryStatement.setInt(4, Integer.parseInt(info[3]));
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                broadcastID = Integer.parseInt(queryResultSet.getString("broadcast_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (info.length == 5) {
            insertMovie(broadcastID);
        }
        else if (info.length == 6) {
            insertLiveShow(broadcastID, info[4]);
        }
        else {
            insertEpisode(info[4], broadcastID, Integer.parseInt(info[5]), Integer.parseInt(info[6]));
        }

        return true;
    }

    public void insertMovie(int id) {
        //Writes info to movies table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "movies " +
                            "(broadcast_id) " +
                            "VALUES " +
                            "(?)"
            );
            insertStatement.setInt(1, id);       //broadcast_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLiveShow(int id, String location) {
        //Writes info to liveshows table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "liveshows " +
                            "(broadcast_id, location) " +
                            "VALUES " +
                            "(?, ?)"
            );
            insertStatement.setInt(1, id);              //broadcast_id
            insertStatement.setString(2, location);     //broadcast_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEpisode(String showName, int broadcastID, int season, int episode) {
        //title + ":" + bio + ":" + launchYear.toString() + ":" + userID + ":" + showName + ":" + season + ":" + episode + ":EPISODE";
        //  0            1                   2                      3               4               5               6           7

        //Writes info to series table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "series " +
                            "(show_name, broadcast_id) " +
                            "VALUES " +
                            "(?, ?)"
            );
            insertStatement.setString(1, showName);       //showName
            insertStatement.setInt(2, broadcastID);       //broadcast_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Gets seriesID
        int seriesID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT series_id " +
                    "FROM series " +
                    "WHERE show_name = ? " +
                    "AND broadcast_id = ?");
            queryStatement.setString(1, showName);
            queryStatement.setInt(2, broadcastID);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                seriesID = Integer.parseInt(queryResultSet.getString("series_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Writes to the seasons table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "seasons " +
                            "(series_id, season_number) " +
                            "VALUES " +
                            "(?, ?)"
            );
            insertStatement.setInt(1, seriesID);       //series_id
            insertStatement.setInt(2, season);         //season_number
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Gets season_id
        int seasonID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT season_id " +
                    "FROM seasons " +
                    "WHERE series_id = ? " +
                    "AND season_number = ?");
            queryStatement.setInt(1, seriesID);
            queryStatement.setInt(2, season);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                seasonID = Integer.parseInt(queryResultSet.getString("season_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Kommer vi her til?");
        System.out.println("episode: " + episode);
        System.out.println("seasonid: " + seasonID);

        //Writes to the episodes table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "episodes " +
                            "(episode_number, season_id) " +
                            "VALUES " +
                            "(?, ?)"
            );
            insertStatement.setInt(1, episode);       //episode_number
            insertStatement.setInt(2, seasonID);      //season_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


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


    public int createUser(String user) {
        //String user = email:password:first_name:last_name
        String[] info = user.split(":");

        //Writes info to account table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "accounts " +
                            "(email, password, first_name, last_name) " +
                            "VALUES " +
                            "(?, ?, ?, ?)"
            );
            insertStatement.setString(1, info[0]);      //email
            insertStatement.setString(2, info[1]);      //password
            insertStatement.setString(3, info[2]);      //first_name
            insertStatement.setString(4, info[3]);      //last_name
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Gets accountID
        int accountID = 0;
        try {
            //PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM broadcasts WHERE " + variable +  " = ?");
            PreparedStatement queryStatement = connection.prepareStatement("SELECT account_id " +
                    "FROM accounts " +
                    "WHERE email = ? " +
                    "AND password = ? " +
                    "AND first_name = ?" +
                    "AND last_name = ?");
            queryStatement.setString(1, info[0]);
            queryStatement.setString(2, info[1]);
            queryStatement.setString(3, info[2]);
            queryStatement.setString(4, info[3]);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                accountID = Integer.parseInt(queryResultSet.getString("account_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Writes info to users table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "users " +
                            "(account_id) " +
                            "VALUES " +
                            "(?)"
            );
            insertStatement.setInt(1, accountID);      //account_id

            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountID;
    }

    public void deleteUser(int userID) {
        //String user = email:password:first_name:last_name

        //Delete from broadcasts table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "DELETE FROM " +
                            "broadcasts " +
                            "WHERE account_id = ?"
            );
            insertStatement.setInt(1, userID);      //account_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Delete from users table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "DELETE FROM " +
                            "users " +
                            "WHERE account_id = ?"
            );
            insertStatement.setInt(1, userID);      //account_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Delete from accounts table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "DELETE FROM " +
                            "accounts " +
                            "WHERE account_id = ?"
            );
            insertStatement.setInt(1, userID);      //account_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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

                //Looks through the text file until it finds a line with the same userID
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

    @Override
    public void addNotification(boolean seen, String date, int user, String change) {
        File file = new File("./src/txtfiles/notifications/notifications.txt");

        if (file.length() == 0) {
            write2file("notifications", seen + ":" + date + ":" +  user + ":" + change,
                    "notifications", true);
        } else {
            write2file("notifications", "\r\n" + seen + ":" + date + ":" +  user + ":" + change,
                    "notifications", true);
        }

    }

    @Override
    public void unNotify(String notification) {
        File file = new File("./src/txtfiles/notifications/notifications.txt");
        Scanner scan;
        String fullFile = "";

        //If the file exists code is run, if not the method exits returning a false
        try {
            scan = new Scanner(file);
            scan.nextLine();
            //Looks through the textfile until it finds a line with the same notification
            while(scan.hasNextLine()) {
                String line = scan.nextLine();

                if (line.equals(notification)) {
                    String newLine =  "true:" + line.substring(line.indexOf(":") + 1);
                    fullFile += "\r\n"+ newLine;
                } else {
                    fullFile += "\r\n" + line;
                }
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        write2file("notifications", fullFile, "notifications", false);

    }


}
