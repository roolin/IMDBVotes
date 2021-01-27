package db;

import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VotesRowMapper implements RowMapper<Votes> {
    @Override
    public Votes mapRow(ResultSet rs, int i) throws SQLException {
        Votes votes = new Votes();

        votes.setId(rs.getInt("id"));
        votes.setIdMovie(rs.getInt("id_movie"));
        votes.setRate(rs.getInt("rate"));
        votes.setVotes(rs.getInt("votes"));

        return votes;
    }
}
