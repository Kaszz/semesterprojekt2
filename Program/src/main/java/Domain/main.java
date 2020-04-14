package Domain;

import Data.DataFacade;
import Interfaces.IReader;
import Interfaces.IWriter;

public class main {

    public static IReader read;
    public static IWriter write;

    public static DataFacade df;

    public static IReader getReader() {
        return read;
    }

    public static IWriter getWriter() {
        return write;
    }
    public static void main(String[] args) {

            df = new DataFacade();
            read = df.getReader();
            write = df.getWriter();

        App.load(args);
    }
}
