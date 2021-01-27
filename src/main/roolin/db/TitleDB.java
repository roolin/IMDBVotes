package db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class TitleDB implements DBHandler<Title> {
    DataSource dataSource;
    SimpleJdbcInsert simpleJdbcInsert;

    public TitleDB(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("title").usingGeneratedKeyColumns("id");
    }

    @Override
    public Title getById(int id) {

        String query = "select * from title where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Title title = jdbcTemplate.queryForObject(query, new TitleRowMapper(), id);

        return title;
    }

    @Override
    public long insert(Title m) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("title_id", m.getTitleId());
        parameters.put("ordering", m.getOrdering());
        parameters.put("title", m.getTitle());
        parameters.put("region", m.getRegion());
        parameters.put("language", m.getLanguage());
        parameters.put("types", m.getTypes());
        parameters.put("attributes", m.getAttributes());
        parameters.put("is_original_title", m.getOriginalTitle());

        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
}
