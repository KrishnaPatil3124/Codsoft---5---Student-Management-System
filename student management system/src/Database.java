import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:data/students.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void init() {
        try (Connection c = connect(); Statement s = c.createStatement()) {

            s.execute("PRAGMA foreign_keys = ON;");

            s.execute("""
                CREATE TABLE IF NOT EXISTS users(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL
                );
            """);

            s.execute("""
                CREATE TABLE IF NOT EXISTS students(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    roll TEXT UNIQUE NOT NULL,
                    grade TEXT,
                    email TEXT
                );
            """);

        } catch (Exception e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }
}
