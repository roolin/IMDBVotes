package db;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class MovieDB implements DBHandler<Movie> {
    private DataSource dataSource;
    private SimpleJdbcInsert simpleJdbcInsert;

    public MovieDB(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("movie").usingGeneratedKeyColumns("id");
    }

    @Override
    public Movie getById(int id) {
        return null;
    }

    @Override
    public long insert(Movie m) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("tconst", m.getTconst());
        parameters.put("title_type", m.getTitleType());
        parameters.put("primary_title", m.getPrimaryTitle());
        parameters.put("original_title", m.getOriginalTitle());
        parameters.put("is_adult", m.isAdult());
        parameters.put("start_year", m.getStartYear());
        parameters.put("end_year", m.getEndYear());
        parameters.put("runtime_minutes", m.getRuntimeMinutes());
        parameters.put("genres", m.getGenres());

        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
}
