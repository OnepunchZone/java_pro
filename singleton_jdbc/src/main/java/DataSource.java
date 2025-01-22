import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final DataSource INSTANCE = new DataSource();

    public static DataSource getInstance() {

        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(QueriesToBd.DATABASE_URL, "sazon", "sazon12345");
    }
}
