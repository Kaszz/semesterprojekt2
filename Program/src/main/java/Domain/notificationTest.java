package Domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class notificationTest {




    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(date);

        //Notification.addNotification(date, "2", "Added credit to De unge mødre.");
        //Notification.addNotification(date, "5", "Added a notification to notifications.");

        Notification.getNotifications();

        User u = new User("17", "email@com.dk", "kodeord", "Christoffer", "Cas");

        Notification.unNotify(false, "15/04/2020", "17", "Oprettet en filmen Tester fra året 2021");

    }
}
