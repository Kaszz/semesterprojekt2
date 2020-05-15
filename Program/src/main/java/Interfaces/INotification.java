package Interfaces;

import Domain.Notification;

import java.util.ArrayList;

public interface INotification {
    public boolean isSeen();
    public String getDate();
    public String getUser();
    public String getChange();
    public int getNotificationID();

    public static ArrayList<INotification> getNotifications() {
        return null;
    }

    public static void unNotify(boolean seen, String date, String user, String change) {}

    public static int notificationCount() {
        return 0;
    }
}
