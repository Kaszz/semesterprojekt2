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
            try {
                Movie broadcast = new Movie(stringSplit[0], new URL(stringSplit[1]), stringSplit[2], Year.of((Integer.parseInt(stringSplit[3]))));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (stringSplit[stringSplit.length-1].equals("LiveShow")) {
            try {
                LiveShow broadcast = new LiveShow(stringSplit[0], new URL(stringSplit[1]), stringSplit[2], Year.of((Integer.parseInt(stringSplit[3]))), stringSplit[4]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (stringSplit[stringSplit.length-1].equals("Episode")) {
            try {
                Episode broadcast = new Episode(stringSplit[0], new URL(stringSplit[1]), stringSplit[2], Year.of((Integer.parseInt(stringSplit[3]))),stringSplit[4], Integer.parseInt(stringSplit[5]), Integer.parseInt(stringSplit[6]));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return broadcast;

    }
}
