package Domain;

import Data.DataFacade;
import Interfaces.IReader;
import Interfaces.IWriter;

public class main {
    static DataFacade df = DataFacade.getInstance();
    public static IReader read = df.getReader();
    public static IWriter write = df.getWriter();
    public static Admin admin = new Admin("admin", "admin", "admin", "admin", "admin");

    public static void main(String[] args) {
        Presentation.App.load(args);
    }


    public static IReader getReader() {
        return read;
    }

    public static IWriter getWriter() {
        return write;
    }
}
