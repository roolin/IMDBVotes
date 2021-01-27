package db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TitleRowMapper implements RowMapper<Title> {
    @Override
    public Title mapRow(ResultSet rs, int i) throws SQLException {
        Title m = new Title();

        m.setId(rs.getInt("id"));
        m.setTitleId(rs.getString("title_id"));
        m.setOrdering(rs.getInt("ordering"));
        m.setTitle(rs.getString("title"));
        m.setRegion(rs.getString("region"));
        m.setLanguage(rs.getString("language"));
        m.setTypes((String[])rs.getArray("types").getArray());
        m.setAttributes((String[])rs.getArray("attributes").getArray());
        m.setOriginalTitle(rs.getBoolean("is_original_title"));

        return m;
    }
}
