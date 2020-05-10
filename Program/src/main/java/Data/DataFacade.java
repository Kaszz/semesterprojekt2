package Data;

public class DataFacade {
    private static DataFacade instance = new DataFacade();
    private Writer writer;
    private Reader reader;
    private ConnectionDB cDB;

    private DataFacade() {
        writer = new Writer();
        reader = new Reader();
        cDB = ConnectionDB.getInstance();
    }

    public Writer getWriter() {
        return writer;
    }

    public Reader getReader() {
        return reader;
    }

    public static DataFacade getInstance() {
        return instance;
    }

    public ConnectionDB getConnectionDB() {
        return cDB;
    }
}

