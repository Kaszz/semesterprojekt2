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

        Movie m = new Movie("Mothers", website, "they be yunging", year);

        m.addCredit("Anders", "Wylardt", CreditType.DIRECTOR);
        m.addCredit("Christopher", "Kas", CreditType.SUPPORT_CAST);
        m.addCredit("Mogens", "Johnyboi", CreditType.EXEC_PRODUCER);

        ArrayList<Credit> hallihallå = m.getCredits();

        for (int i = 0; i < hallihallå.size(); i++) {
            System.out.println(hallihallå.get(i).getlName());
        }


    }

}
