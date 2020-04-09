package Domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.Year;

public class AccountTest {
    public static void main(String[] args) {
        Movie peterplys;
        LiveShow live;
        Episode homer;

        Admin mikail = new Admin("mik123", "pass123", "mikail", "mik@themik.gmail.com");
        Credit Simon = new Credit("Simon", "Jessen", CreditType.SUPPORT_CAST);
        User SimonTheUser = new User("Jessen", "22456", "Simon", "simon@gmail.com", "1");
        User SimonTheUser2 = new User("Jessen", "pass", "Simone", "simon@gmoil.com", "2");
        try {
            peterplys = new Movie("peterplys",
                    new URL("http://google.com"),
                    "tusindårs skoven og noget",
                    Year.of(1995));
            live = new LiveShow("live",
                    new URL("http://google.com"),
                    "vi er live",
                    Year.of(1995), "her");
            homer = new Episode("homer",
                    new URL("http://google.com"),
                    "doh again and again",
                    Year.of(1995), "Simpson", 5, 3);

            //mikail.createMovie(peterplys.getTitle(), peterplys.getTrailerURL(), peterplys.getBio(), peterplys.getLaunchYear());
            //mikail.addCredit(peterplys.getTitle(), Simon.getfName(), Simon.getlName(), Simon.getRole());
            //mikail.createLiveShow(live.getTitle(), live.getTrailerURL(), live.getBio(), live.getLaunchYear(), live.getLocation());
            //mikail.addCredit(live.getTitle(), Simon.getfName(), Simon.getlName(), Simon.getRole());
            //mikail.createEpisode(homer.getTitle(), homer.getTrailerURL(), homer.getBio(), homer.getLaunchYear(), homer.getShowName(), homer.getSeason(), homer.getEpisode());
            //mikail.addCredit(homer.getTitle(), Simon.getfName(), Simon.getlName(), Simon.getRole());
            //mikail.deleteCredit(homer.getTitle(), Simon);
            //mikail.deleteBroadcast(peterplys.getTitle());
            //mikail.createUser(SimonTheUser2.getUserID(), SimonTheUser.getUsername(), SimonTheUser.getPassword(), SimonTheUser.getName(), SimonTheUser.getEmail());
            //mikail.deleteUser(SimonTheUser2.getUserID(), SimonTheUser.getUsername(), SimonTheUser.getPassword(), SimonTheUser.getName(), SimonTheUser.getEmail());
            mikail.editUser(SimonTheUser2.getUserID(), SimonTheUser2.getUsername(), SimonTheUser2.getPassword(), SimonTheUser2.getName(), SimonTheUser2.getEmail());

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
    }
}