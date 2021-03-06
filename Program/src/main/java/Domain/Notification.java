package Domain;

import Interfaces.INotification;
import Interfaces.IReader;
import Interfaces.IWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Notification implements INotification {
    //Attributes
    private boolean seen;
    private String date;
    private String user;
    private String change;
    int notificationID;
    private static ArrayList<Notification> notifications = new ArrayList<>();
    private static IWriter write = main.getWriter();
    private static IReader read = main.getReader();

    //Constructors
    public Notification(Date time, String user, String change) {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.date = DateFor.format(time);
        this.seen = false;
        this.user = user;
        this.change = change;
    }

    public Notification(int notificationID, boolean seen, Date time, String user, String change) {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.seen = seen;
        this.date = DateFor.format(time);
        this.user = user;
        this.change = change;
        this.notificationID = notificationID;
    }


    //Methods
    public boolean isSeen() {
        return seen;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public int getNotificationID() {return notificationID;}

    public String getChange() {
        return change;
    }

    public static synchronized ArrayList<INotification> getNotifications() {
        notifications.clear();
        ArrayList<String> tempList = read.getNotifications();

        for (String s: tempList) {
            String[] info = s.split(":");
            Date tempDate = new Date();
            try {
                tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(info[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            boolean state = Boolean.parseBoolean(info[1]);
            Notification n = new Notification(Integer.parseInt(info[0]), state, tempDate, info[3], info[4]);
            notifications.add(n);
        }

        ArrayList<INotification> temp;
        temp = new ArrayList<INotification>(notifications);

        return temp;
    }

    public static void addNotification(Date time, String user, String change) {
        Notification n = new Notification(time, user, change);
        write.addNotification(n.isSeen(), n.getDate(), n.getUser(), n.getChange());
    }

    public static void unNotify(int notificationID) {
        write.unNotify(notificationID);
    }

    public static synchronized int notificationCount() {
        getNotifications();
        int count = 0;
        for (INotification i : notifications) {
            if (!i.isSeen())
                count++;
        }

        return count;
    }


}
