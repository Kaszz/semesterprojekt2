package Domain;

import Interfaces.ICredit;

import java.util.UUID;

public class Credit implements ICredit {
    //Attributes
    private int creditID;
    private String fName;
    private String lName;
    private CreditType role;

    //Constructors
    public Credit(String fName, String lName, CreditType role) {
        this.fName = fName;
        this.lName = lName;
        this.role = role;
    }

    public Credit(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public Credit(int creditID, String fName, String lName, CreditType role) {
        this.creditID = creditID;
        this.fName = fName;
        this.lName = lName;
        this.role = role;
    }

    //Methods
    public int getCreditID() {
        return creditID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public CreditType getRole() {
        return role;
    }
}
