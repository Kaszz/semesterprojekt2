package Interfaces;

import Domain.Notification;

import java.util.ArrayList;

public interface INotification {
    public boolean isSeen();
    public String getDate();
    public String getUser();
    public String getChange();

    public static ArrayList<INotification> getNotifications() {
        return null;
    }
}
