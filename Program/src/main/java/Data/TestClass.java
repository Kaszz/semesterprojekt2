package Data;

import Interfaces.IWriter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {
        Writer write = new Writer();
        Reader read = new Reader();

        //write.hello();
        //System.out.println(write.createBroadcast("Mothers:http://facebook.com:they_cook_food_:2012"));
        //System.out.println(write.deleteBroadcast("Mothers"));

        //System.out.println(write.addCredit("Mothers", "Christopher:Kas:SUPPORT_CAST"));
        //System.out.println(write.addCredit("Mothers", "Simon:Jessen:ACTOR"));
        //System.out.println(write.addCredit("Mothers", "Anders:Wylard:LIGHTING"));
        //System.out.println(write.addCredit("Mothers", "Aleksander:Soender:CAMERA"));

        //System.out.println(write.deleteCredit("Mothers", "Christopher:Kas:SUPPORT_CAST"));


        System.out.println(read.getPersonalCredits());

        /*
        ArrayList<String> test = new ArrayList<>();
        test = read.getPersonalCredits("Christopher", "Kas");


        for (String s: test) {
            System.out.println(s);
        }
        */

    }
}
