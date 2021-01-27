package db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class VotesDB implements DBHandler<Votes> {
    private DataSource dataSource;
    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;

    public VotesDB(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("votes").usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Votes getById(int id) {
        return null;
    }

    @Override
    public long insert(Votes v) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_movie", v.getIdMovie());
        parameters.put("rate", v.getRate());
        parameters.put("votes", v.getVotes());

        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    public void updateVotes(Votes v) {
        this.jdbcTemplate.update("update votes set votes = ? where id = ?", v.getVotes(), v.getId());
    }

    public Votes[] getVotesForMovie(Movie movie) {
        String query = "select * from votes where id_movie = ?";

        return jdbcTemplate.query(query, new VotesRowMapper(), movie.getId()).toArray(new Votes[] {});
    }
}
