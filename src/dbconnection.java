import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:2020/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dima2345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
