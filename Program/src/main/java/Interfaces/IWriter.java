package Interfaces;

public interface IWriter {
    public boolean createBroadcast(String broadcast);
    public String deleteBroadcast(String title);
    public boolean addCredit(String title, String credit);
    public String deleteCredit(String title, String credit);
}