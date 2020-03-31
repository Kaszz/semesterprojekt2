package com.mycompany.program01;

public abstract class Broadcast implements Login {
    private String title;
    private Credit credits[];
    private String trailerURL;
    private String bio;
    private int launchYear;
    private String userID;


    public void addCredit(Credit credit, String fName, String lName, String role) {

    }

    /*public Broadcast getBroadcast(String title) {


    }*/

    public String getTitle() {
        return title;
    }
}
