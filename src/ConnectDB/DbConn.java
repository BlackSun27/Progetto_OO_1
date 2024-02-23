package ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn{
    public Connection conn;
    public Connection conn_db(String dbname, String user, String pass) throws SQLException{
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
        System.out.println("Connessione in corso");
        if (conn != null) {
            System.out.println("Connessione effettuata!");
        } else {
            System.out.println("Connessione fallita!");
        }
        return conn;
    }
}
