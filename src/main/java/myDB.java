import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class myDB {
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        Connection conn;

        void connect(String user, String password) throws SQLException {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
            }
            conn = DriverManager.getConnection(dbUrl, user, password);
        }

        void insert() {    //The insert function is just a test, needs to be implemented
            try {
                Statement s = conn.createStatement();
                String sqlStr = "INSERT INTO patients (familyname, givenname, phonenumber) values ('Cuggino', 'Abbessati', 332245);";
                s.execute(sqlStr);
                s.close();
                conn.close();
            } catch (Exception e) {
            }
        }
    }
