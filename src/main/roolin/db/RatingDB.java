package db;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RatingDB implements DBHandler<Rating>{
    private DataSource dataSource;
    private SimpleJdbcInsert simpleJdbcInsert;

    public RatingDB(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("rating").usingGeneratedKeyColumns("id");
    }

    @Override
    public Rating getById(int id) {
        return null;
    }

    @Override
    public long insert(Rating m) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_movie", m.getIdMovie());
        parameters.put("tconst", m.getTconst());
        parameters.put("average_rating", m.getAverageRating());
        parameters.put("numVotes", m.getNumVotes());

        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
}
