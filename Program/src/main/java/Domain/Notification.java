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

    public Notification(boolean seen, Date time, String user, String change) {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        this.date = DateFor.format(time);
        this.seen = false;
        this.user = user;
        this.change = change;
        this.seen = false;
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

    public String getChange() {
        return change;
    }

    public static ArrayList<INotification> getNotifications() {
        ArrayList<String> tempList = read.getNotifications();

        for (String s: tempList) {
            String[] info = s.split(":");

            Date tempDate = new Date();
            try {
                tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(info[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Notification n = new Notification(Boolean.parseBoolean(info[0]), tempDate, info[2], info[3]);
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

}
