import db.DBHandler;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IMDBToDB<T> {
    private Class c;

    public IMDBToDB(Class c) {
        this.c = c;
    }

    public void process(DataSource ds) {
        goWithIt(ds);
    }

    public void goWithIt(DataSource ds) {
        IMDBDataFile<T> idf = new IMDBDataFile(c);
        try {
            idf.download();
            idf.unpack();
//            List<T> moviesList = idf.parseToTable(new File("M:\\Download\\imdb\\title.basics.tsv"));
            List<T> moviesList = idf.parseToTable();
            FilesTypes ft = new FilesTypes<T>();
            DBHandler<T> handler = ft.getHandler(c, ds);
            for(T m : moviesList) {
                handler.insert(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
