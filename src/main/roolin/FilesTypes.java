import db.*;

import javax.sql.DataSource;

public class FilesTypes<T> {
    public String getFileName(Class c) {
        if(c.equals(Movie.class)) {
            return "title.basics.tsv";
        }
        if(c.equals(Title.class)) {
            return "title.akas.tsv";
        }
        if(c.equals(Rating.class)) {
            return "title.ratings.tsv";
        }

        return null;
    }

    public DBHandler getHandler(Class c, DataSource dataSource) {
        if(c.equals(Movie.class)) {
            return new MovieDB(dataSource);
        }
        if(c.equals(Rating.class)) {
            return new RatingDB(dataSource);
        }
        if(c.equals(Title.class)) {
            return new TitleDB(dataSource);
        }
        return null;
    }

//    "title.akas.tsv.gz"
//    "title.basics.tsv.gz"
//    "title.crew.tsv.gz"
//    "title.episode.tsv.gz"
//    "title.principals.tsv.gz"
//    "title.ratings.tsv.gz"
//    "name.basics.tsv.gz"
}
