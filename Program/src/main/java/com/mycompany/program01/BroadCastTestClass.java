package com.mycompany.program01;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;

public class BroadCastTestClass {

        public static void main(String[] args) {
        URL website = null;
        {
            try {
                website = new URL("http://www.google.com");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        Year year = Year.of(2021);


        User s = new User("test", "admin123", "Simon", "simon@simon.com", "2");
        s.createMovie("Fathers", website, "they be oldin", year);

        s.addCredit("Fathers", "Anders", "Wylardt", CreditType.DIRECTOR);
        s.addCredit("Fathers", "Christopher", "Kas", CreditType.SUPPORT_CAST);
        s.addCredit("Fathers", "Mogens", "Johnyboi", CreditType.EXEC_PRODUCER);

    }

}
