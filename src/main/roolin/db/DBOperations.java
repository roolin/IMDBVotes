package db;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

public class DBOperations {
    public void connectRatingsToMovies(DataSource ds) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.update("update rating r set id_movie = m.id from movie m where m.tconst = r.tconst;");
    }

    public Movie[] getMoviesForVotesDownloadOnlyNew(DataSource ds, int minVotes, int limit) {
        String query = "select m.* \n" +
                "  from movie m \n" +
                "  join rating r on m.tconst = r.tconst \n" +
                "  left join votes v on v.id_movie = m.id\n" +
                " where m.title_type = 'movie' \n" +
                "   and r.numvotes > ? \n" +
                "   and v.id is null\n" +
                " order by r.average_rating desc\n" +
                " limit ?";

        return getMoviesForQuery(ds, query, new Object[] {minVotes, limit}, new int[] {Types.INTEGER, Types.INTEGER});
    }

    public Movie[] getMoviesForVotesDownload(DataSource ds, int minVotes, int limit) {
        String query = "select m.* \n" +
                "  from movie m \n" +
                "  join rating r on m.tconst = r.tconst \n" +
                " where m.title_type = 'movie' \n" +
                "   and r.numvotes > ? \n" +
                " order by r.average_rating desc\n" +
                " limit ?";

        return getMoviesForQuery(ds, query, new Object[] {minVotes, limit}, new int[] {Types.INTEGER, Types.INTEGER});
    }

    private Movie[] getMoviesForQuery(DataSource ds, String query, Object[] args, int[] argTypes) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        List<Movie> employee = jdbcTemplate.query(
                query, args, argTypes, new MovieRowMapper());
        return employee.toArray(new Movie[] { });
    }
}
