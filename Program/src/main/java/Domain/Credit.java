package Domain;

import java.util.UUID;

public class Credit {
    //Attributes
    private UUID creditID;
    private String fName;
    private String lName;
    private CreditType role;

    //Constructors
    public Credit(String fName, String lName, CreditType role) {
        creditID = UUID.randomUUID();
        this.fName = fName;
        this.lName = lName;
        this.role = role;
    }


    //Methods
    public UUID getCreditID() {
        return creditID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

}