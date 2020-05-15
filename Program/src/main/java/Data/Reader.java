package Data;

import Interfaces.IReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader implements IReader {

    Connection connection = ConnectionDB.getConnection();

    /**
     * Method to get all the credits of a given broadcast.
     * @return the list of credits in the broadcast.
     */
    @Override
    public ArrayList<String> getBroadcastCredits(int broadcastID) {
        ArrayList<String> returnList = new ArrayList<String>();
        String creditLine = "";

        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT credit_id, first_name, last_name, credit_type\n" +
                    "FROM credits\n" +
                    "INNER JOIN broadcasts_credits USING(credit_id)\n" +
                    "INNER JOIN broadcasts USING(broadcast_id)\n" +
                    "WHERE broadcast_id = ?;");
            queryStatement.setInt(1, broadcastID);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while (queryResultSet.next()) {
                creditLine = queryResultSet.getString("credit_id") + ":"
                           + queryResultSet.getString("first_name") + ":"
                           + queryResultSet.getString("last_name") + ":"
                           + queryResultSet.getString("credit_type");
                returnList.add(creditLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnList;
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> returnList = new ArrayList<String>();
        String accountLine = "";

        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT user_id, email, password, first_name, last_name FROM accounts INNER JOIN users USING (account_id);");
            ResultSet queryResultSet = queryStatement.executeQuery();

            while (queryResultSet.next()) {
                accountLine = queryResultSet.getString("user_id") + ":"
                        + queryResultSet.getString("email") + ":"
                        + queryResultSet.getString("password") + ":"
                        + queryResultSet.getString("first_name") + ":"
                        + queryResultSet.getString("last_name");
                returnList.add(accountLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return returnList;
    }

    public ArrayList<String> getAllBroadcasts() {
        ArrayList<String> broadcasts = new ArrayList<>();

        //Movie
        broadcasts.addAll(getPartialBroadcastInfo("SELECT broadcast_id, title, bio, launchyear, account_id FROM movies INNER JOIN broadcasts USING (broadcast_id);"));

        //Liveshow
        broadcasts.addAll(getPartialBroadcastInfo("SELECT broadcast_id, title, bio, launchyear, location, account_id FROM liveshow INNER JOIN broadcasts USING (broadcast_id);"));

        //Series/seasons/episodes
        broadcasts.addAll(getPartialBroadcastInfo("SELECT episode_id, episode_name, episode_no, season_no, series_id, title, bio, launchyear,  account_id\n" +
                "FROM episodes\n" +
                "    INNER JOIN seasons ON episodes.season_id = seasons.season_id\n" +
                "    INNER JOIN series ON series.broadcast_id = seasons.broadcast_id\n" +
                "    INNER JOIN broadcasts ON series.broadcast_id = broadcasts.broadcast_id;"));
        return broadcasts;
    }

    public ArrayList<String> getPartialBroadcastInfo(String sql) {
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            PreparedStatement queryTable = connection.prepareStatement(sql);

            ResultSet tableResult = queryTable.executeQuery();
            ResultSetMetaData tableMetaData = queryTable.getMetaData();

            while (tableResult.next()) {
                String string = "";
                string = string + tableResult.getString(1);

                for (int i = 2; i < tableMetaData.getColumnCount()+1; i++) {
                    string = string + ":" + tableResult.getString(i);
                }
                arrayList.add(string);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public String checkUser(String emailAddress, String password) {
        String returnString = "";

        //Check if user exists
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT account_id, email, password, first_name, last_name FROM accounts\n" +
                    "WHERE email = ?\n" +
                    "AND password = ?;");
            queryStatement.setString(1, emailAddress);
            queryStatement.setString(2, password);
            ResultSet queryResultSet = queryStatement.executeQuery();

            while(queryResultSet.next()) {
                returnString = queryResultSet.getString("account_id") + ":"
                        + queryResultSet.getString("email") + ":"
                        + queryResultSet.getString("password") + ":"
                        + queryResultSet.getString("first_name") + ":"
                        + queryResultSet.getString("last_name") + ":";
                return returnString;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "User not found.";
    }

    @Override
    public synchronized ArrayList<String> getNotifications() {
        ArrayList<String> returnList = new ArrayList<String>();
        String notificationLine = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            PreparedStatement queryTable = connection.prepareStatement("SELECT notification_id, seen, created, user_name, change FROM notifications ORDER BY notification_id DESC LIMIT 20;");

            ResultSet tableResult = queryTable.executeQuery();

            while (tableResult.next()) {
                java.sql.Date date = tableResult.getDate("created");
                java.util.Date utilDate = new java.util.Date(date.getTime());
                String dateString = formatter.format(utilDate);


                notificationLine = tableResult.getInt("notification_id") + ":"
                                 + tableResult.getBoolean("seen") + ":"
                                 + dateString + ":"
                                 + tableResult.getString("user_name") + ":"
                                 + tableResult.getString("change");

                returnList.add(notificationLine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return returnList;
    }


}
