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
import java.time.Year;
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

    public void editMovie(int broadcast_id, String title, String bio, int launchYear) {
        try {
            PreparedStatement queryBroadcast = connection.prepareStatement("UPDATE broadcasts SET title = ?, bio = ?, launchyear = ? WHERE broadcast_id = ?;");

            queryBroadcast.setString(1, title);
            queryBroadcast.setString(2, bio);
            queryBroadcast.setInt(3, launchYear);
            queryBroadcast.setInt(4, broadcast_id);
            queryBroadcast.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLiveShow(int broadcast_id, String title, String bio, int launchYear, String location) {
        try {
            //Updates broadcast table
            PreparedStatement queryBroadcast = connection.prepareStatement("UPDATE broadcasts SET title = ?, bio = ?, launchyear = ? WHERE broadcast_id = ?;");
            queryBroadcast.setString(1, title);
            queryBroadcast.setString(2, bio);
            queryBroadcast.setInt(3, launchYear);
            queryBroadcast.setInt(4, broadcast_id);
            queryBroadcast.execute();

            //Updates liveshow table
            PreparedStatement queryLiveShow = connection.prepareStatement("UPDATE liveshow SET location = ? WHERE broadcast_id = ?;");
            queryLiveShow.setString(1, location);
            queryLiveShow.setInt(2, broadcast_id);
            queryLiveShow.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editEpisode(int episodeID, String title, String bio, int launchYear, int seaNum, int epiNum) {
        int broadcastID = 0;
        try {
            //Get broadcastID through episode table
            PreparedStatement queryStatement = connection.prepareStatement("SELECT broadcast_id FROM episodes WHERE episode_id = ?;");
            queryStatement.setInt(1, episodeID);
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next())
                broadcastID = Integer.parseInt(queryResultSet.getString("broadcast_id"));


            //Change, bio, launchyear in broadcast table
            PreparedStatement queryBroadcast = connection.prepareStatement("UPDATE broadcasts SET bio = ?, launchyear = ? WHERE broadcast_id = ?;");
            queryBroadcast.setString(1, bio);
            queryBroadcast.setInt(2, launchYear);
            queryBroadcast.setInt(3, broadcastID);
            queryBroadcast.execute();


            //Check season table for broadcastID and season number
            int seasonID = 0;
            PreparedStatement querySeason = connection.prepareStatement("SELECT season_id FROM seasons WHERE broadcast_id = ? AND season_no = ?;");
            querySeason.setInt(1, broadcastID);
            querySeason.setInt(2, seaNum);
            ResultSet querySeasonResult = querySeason.executeQuery();
            while(querySeasonResult.next())
                seasonID = Integer.parseInt(querySeasonResult.getString("season_id"));


            //If the season doesnt already exist.
            if (seasonID == 0) {
                PreparedStatement queryNewSeason = connection.prepareStatement("INSERT INTO seasons (broadcast_id, season_no) VALUES (?, ?);");
                queryNewSeason.setInt(1, broadcastID);
                queryNewSeason.setInt(2, seaNum);
                queryNewSeason.execute();

                //Get the new seasonID
                PreparedStatement querySeason2 = connection.prepareStatement("SELECT season_id FROM seasons WHERE broadcast_id = ? AND season_no = ?;");
                querySeason2.setInt(1, broadcastID);
                querySeason2.setInt(2, seaNum);
                ResultSet querySeasonResult2 = querySeason.executeQuery();
                while(querySeasonResult2.next())
                    seasonID = Integer.parseInt(querySeasonResult2.getString("season_id"));
            }

            //Update episode table with season_id, title, episode number
            PreparedStatement queryEpisode = connection.prepareStatement("UPDATE episodes SET season_id = ?, episode_name = ?, episode_no = ? WHERE episode_id = ?;");
            queryEpisode.setInt(1, seasonID);
            queryEpisode.setString(2, title);
            queryEpisode.setInt(3, epiNum);
            queryEpisode.setInt(4, episodeID);
            queryEpisode.execute();

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

    public void editUser(int userID, String email, String password, String fName, String lName) {
        System.out.println(userID);
        System.out.println(email);
        System.out.println(password);
        System.out.println(fName);
        System.out.println(lName);
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE accounts SET email = ?, password = ?, first_name = ?, last_name = ? WHERE account_id = ?;");
            insertStatement.setString(1, email);        //email
            insertStatement.setString(2, password);     //password
            insertStatement.setString(3, fName);        //first_name
            insertStatement.setString(4, lName);        //last_name
            insertStatement.setInt(5, userID);       //account_id
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void unNotify(int broadcastID) {
        try {
            PreparedStatement queryUpdateNotification = connection.prepareStatement("UPDATE notifications SET seen = true WHERE notification_id = ?;");

            queryUpdateNotification.setInt(1, broadcastID);
            queryUpdateNotification.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
