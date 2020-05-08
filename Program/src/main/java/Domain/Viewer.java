package Domain;

import Interfaces.IReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;

public class Viewer {
    private static IReader read = main.getReader();
    private Broadcast broadcast;

    public Broadcast showBroadcast(String title) {
        String[] stringSplit = title.split(":");

        if (stringSplit[stringSplit.length-1].equals("Movie")) {
                Movie broadcast = new Movie(stringSplit[0], stringSplit[1], Year.of((Integer.parseInt(stringSplit[2]))));

        } else if (stringSplit[stringSplit.length-1].equals("LiveShow")) {
                LiveShow broadcast = new LiveShow(stringSplit[0], stringSplit[1], Year.of((Integer.parseInt(stringSplit[2]))), stringSplit[3]);

        } else if (stringSplit[stringSplit.length-1].equals("Episode")) {
                Episode broadcast = new Episode(stringSplit[0], stringSplit[1], Year.of((Integer.parseInt(stringSplit[2]))),stringSplit[3], Integer.parseInt(stringSplit[4]), Integer.parseInt(stringSplit[5]));

        }
        return broadcast;

    }
}
