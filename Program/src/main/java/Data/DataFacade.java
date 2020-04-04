package Data;

import Interfaces.IReader;
import Interfaces.IWriter;

public class DataFacade {
    private Writer writer;
    private Reader reader;

    public DataFacade() {
        writer = new Writer();
        reader = new Reader();
    }

    public IWriter getWriter() {
        return writer;
    }

    public IReader getReader() {
        return reader;
    }
}

