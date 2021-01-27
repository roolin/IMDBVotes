import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

public class DBConn {
    private DriverManagerDataSource dataSource;

    public DBConn() {
        this.dataSource = postgresDataSource();
    }

    private DriverManagerDataSource postgresDataSource() {
        dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/imdb");
        dataSource.setUsername("imdb_user");
        dataSource.setPassword("imdb");

        return dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
