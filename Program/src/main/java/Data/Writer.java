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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Writer implements IWriter {
    String broadcastDirectory = "broadcasts";
    String userDirectory = "users";
    String userFile = "users";
    Connection connection = ConnectionDB.getConnection();

    @Override
    public boolean createBroadcast(String broadcast) {
        String[] info = broadcast.split(":");
        int type = 0;

        if (info.length == 4)
            type = 2; //Movie
        else if (info.length == 5)
            type = 3; //LiveShow
        else
            type = 1; //Episode

        //Check if broadcast already exists
        int broadcastID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT broadcast_id " +
                    "FROM broadcasts " +
                    "WHERE title = ?");
            queryStatement.setString(1, info[0]);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                broadcastID = Integer.parseInt(queryResultSet.getString("broadcast_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (broadcastID != 0 && type == 1) {
            insertEpisode(info[4], broadcastID, Integer.parseInt(info[5]), Integer.parseInt(info[6]), true);
        }
        else if (broadcastID != 0) {
            System.out.println("Broadcasts already exists.");
        }
        else {
            //Writes info to broadcasts table.
            try {
                PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO " +
                                "broadcasts " +
                                "(broadcast_type_id, title, bio, launchyear, account_id) " +
                                "VALUES " +
                                "(?, ?, ?, ?, ?)"
                );
                insertStatement.setInt(1, type);                        //broadcast_type_id
                insertStatement.setString(2, info[0]);                  //title (showName for createEpisode)
                insertStatement.setString(3, info[1]);                  //bio
                insertStatement.setInt(4, Integer.parseInt(info[2]));   //launchyear
                insertStatement.setInt(5, Integer.parseInt(info[3]));   //account_id
                insertStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Gets broadcastID
            broadcastID = 0;
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

                while (queryResultSet.next())
                    broadcastID = Integer.parseInt(queryResultSet.getString("broadcast_id"));

            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (info.length == 4) {
                insertMovie(broadcastID);
            } else if (info.length == 5) {
                insertLiveShow(broadcastID, info[4]);
            } else {
                insertEpisode(info[4], broadcastID, Integer.parseInt(info[5]), Integer.parseInt(info[6]), false);
            }

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
                            "liveshow " +
                            "(broadcast_id, location) " +
                            "VALUES " +
                            "(?, ?)"
            );
            insertStatement.setInt(1, id);              //broadcast_id
            insertStatement.setString(2, location);     //location
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEpisode(String epName, int broadcastID, int seaNum, int epiNum, boolean exists) {

        //If series doesnt exists
        if (!exists) {
            //Writes info to series table.
            try {
                PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO " +
                                "series " +
                                "(broadcast_id) " +
                                "VALUES " +
                                "(?)"
                );
                insertStatement.setInt(1, broadcastID);       //broadcast_id
                insertStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Gets seriesID
        int seriesID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT series_id " +
                    "FROM series " +
                    "WHERE broadcast_id = ?");
            queryStatement.setInt(1, broadcastID);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                seriesID = Integer.parseInt(queryResultSet.getString("series_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Check if season already exists
        int seasonID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT season_id " +
                    "FROM seasons " +
                    "WHERE broadcast_id = ? " +
                    "AND season_no = ?");
            queryStatement.setInt(1, broadcastID);
            queryStatement.setInt(2, seaNum);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                seasonID = Integer.parseInt(queryResultSet.getString("season_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //If season doesnt exists.
        if (seasonID == 0) {
            //Writes to the seasons table.
            try {
                PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO " +
                                "seasons " +
                                "(broadcast_id, season_no) " +
                                "VALUES " +
                                "(?, ?)"
                );
                insertStatement.setInt(1, broadcastID);       //broadcast_id
                insertStatement.setInt(2, seaNum);         //season_no
                insertStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Gets season_id
            seasonID = 0;
            try {
                PreparedStatement queryStatement = connection.prepareStatement("SELECT season_id " +
                        "FROM seasons " +
                        "WHERE broadcast_id = ? " +
                        "AND season_no = ?");
                queryStatement.setInt(1, broadcastID);
                queryStatement.setInt(2, seaNum);
                ResultSet queryResultSet = queryStatement.executeQuery();

                while(queryResultSet.next())
                    seasonID = Integer.parseInt(queryResultSet.getString("season_id"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //Writes to the episodes table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "episodes " +
                            "(season_id, broadcast_id, episode_name, episode_no) " +
                            "VALUES " +
                            "(?, ?, ?, ?)"
            );
            insertStatement.setInt(1, seasonID);        //season_id
            insertStatement.setInt(2, broadcastID);     //broadcast_id
            insertStatement.setString(3, epName);       //episode_name
            insertStatement.setInt(4, epiNum);          //episode_no
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteBroadcast(int broadcast_id) {
        System.out.println("Deleting broadcast: " + broadcast_id);
        try {
            PreparedStatement queryBroadcast = connection.prepareStatement("DELETE FROM broadcasts WHERE broadcast_id = ?");
            queryBroadcast.setInt(1, broadcast_id);
            queryBroadcast.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEpisode(int episode_id) {
        System.out.println("Deleting episode: " + episode_id);
        try {
            PreparedStatement queryBroadcast = connection.prepareStatement("DELETE FROM episodes WHERE episode_id = ?");
            queryBroadcast.setInt(1, episode_id);
            queryBroadcast.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCredit(int broadcastID, String credit) {
        String[] info = credit.split(":");

        //Writes info to credits table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "credits " +
                            "(first_name, last_name, credit_type) " +
                            "VALUES " +
                            "(?, ?, ?)"
            );
            insertStatement.setString(1, info[0]);      //first_name
            insertStatement.setString(2, info[1]);      //last_name
            insertStatement.setString(3, info[2]);      //credit_type
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Gets creditID
        int creditID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT credit_id " +
                    "FROM credits " +
                    "WHERE first_name = ? " +
                    "AND last_name = ? " +
                    "AND credit_type = ?;");
            queryStatement.setString(1, info[0]);
            queryStatement.setString(2, info[1]);
            queryStatement.setString(3, info[2]);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                creditID = Integer.parseInt(queryResultSet.getString("credit_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Writes info to broadcasts_credits table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "broadcasts_credits " +
                            "(broadcast_id, credit_id) " +
                            "VALUES " +
                            "(?, ?)"
            );
            insertStatement.setInt(1, broadcastID);     //broadcast_id
            insertStatement.setInt(2, creditID);        //credit_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCredit(int creditID) {
        System.out.println("Deleting credit: " + creditID);
        try {
            PreparedStatement queryBroadcast = connection.prepareStatement("DELETE FROM credits WHERE credit_id = ?;");
            queryBroadcast.setInt(1, creditID);
            queryBroadcast.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public int createPerson(String person) {
        //String user = email:password:first_name:last_name
        String[] info = person.split(":");

        //Writes info to account table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "persons " +
                            "(credit_id, first_name, last_name) " +
                            "VALUES " +
                            "(?, ?, ?, ?)"
            );
            insertStatement.setString(1, info[0]);      //email
            insertStatement.setString(2, info[1]);      //password
            insertStatement.setString(3, info[2]);      //first_name
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Gets accountID
        int personID = 0;
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT person_id " +
                    "FROM persons " +
                    "WHERE credit_id = ? " +
                    "AND first_name = ?" +
                    "AND last_name = ?");
            queryStatement.setString(1, info[0]);
            queryStatement.setString(2, info[1]);
            queryStatement.setString(3, info[2]);
//            queryStatement.setString(4, info[3]);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next())
                personID = Integer.parseInt(queryResultSet.getString("person_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Writes info to users table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "persons " +
                            "(person_id) " +
                            "VALUES " +
                            "(?)"
            );
            insertStatement.setInt(1, personID);      //account_id

            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personID;
    }

    public void deleteUser(int userID) {
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

    public boolean editUser(String user) {
        File file = new File("./src/txtfiles/"+ userDirectory+"/" + userFile + ".txt");
        Scanner userTxt;
        String newText = "";
        /*
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

         */
        return false;
    }

    @Override
    public void addNotification(boolean seen, String date, String user, String change) {
        //Writes info to notifications table.
        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO " +
                            "notifications " +
                            "(created, change, seen, user_name) " +
                            "VALUES " +
                            "(?, ?, ?, ?)"
            );
            Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
            //java.sql.Date sqlDate = java.sql.Date.valueOf(newDate);

            //SELECT seen, created, user_name, change FROM notifications;

            insertStatement.setDate(1, sqlDate);        //date
            insertStatement.setString(2, change);       //change
            insertStatement.setBoolean(3, seen);        //seen
            insertStatement.setString(4, user);         //user
            insertStatement.execute();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unNotify(String notification) {

    }


}
