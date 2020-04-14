package Data;

public class DataFacade {
    private static DataFacade instance = new DataFacade();
    private Writer writer;
    private Reader reader;

    private DataFacade() {
        writer = new Writer();
        reader = new Reader();
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
}

