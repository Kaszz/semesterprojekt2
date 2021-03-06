package Domain;

import Data.ConnectionDB;
import Data.DataFacade;
import Interfaces.IConnectionDB;
import Interfaces.IReader;
import Interfaces.IWriter;

public class main {
    static DataFacade df = DataFacade.getInstance();
    public static IReader read = df.getReader();
    public static IWriter write = df.getWriter();
    public static IConnectionDB cDB = df.getConnectionDB();
    public static Admin admin = new Admin(0, "admin", "admin", "admin", "admin");

    public static void main(String[] args) {
        write.editMovie(21, "Stoneman", "he is made of stone like real woman", 1987);
        Presentation.App.load(args);
    }


    public static IReader getReader() {
        return read;
    }

    public static IWriter getWriter() {
        return write;
    }
}
