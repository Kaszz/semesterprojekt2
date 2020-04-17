package Interfaces;

import Domain.CreditType;

import java.util.UUID;

public interface ICredit {
    public UUID getCreditID();
    public String getfName();
    public String getlName();
    public CreditType getRole();
}
