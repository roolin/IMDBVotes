package db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTconst(rs.getString("tconst"));
        movie.setTitleType(rs.getString("title_type"));
        movie.setPrimaryTitle(rs.getString("primary_title"));
        movie.setOriginalTitle(rs.getString("original_title"));
        movie.setAdult(rs.getBoolean("is_adult"));
        movie.setStartYear(rs.getString("start_year"));
        movie.setEndYear(rs.getString("end_year"));
        movie.setRuntimeMinutes(rs.getString("runtime_minutes"));
        movie.setGenres((String[])rs.getArray("genres").getArray());

        return movie;
    }
}
