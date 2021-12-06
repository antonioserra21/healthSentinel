import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class myDB {
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        Connection conn = null;

        void connect(String user, String password) throws SQLException {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (Exception e) {
            }
            conn = DriverManager.getConnection(dbUrl, user, password);
        }

        void createTable(String tableName) throws SQLException {
            String sqlStr = new StringBuilder().append("create table ").append(tableName)
                    .append(" ( id SERIAL PRIMARY KEY, name varchar(20), surname varchar(20), sex varchar(29));").toString();
                    //.append("age int(200) );").toString();
            // can add a loop and a parameter tableDim to create a table with a specified number
            Statement s = conn.createStatement();
            s.execute(sqlStr);
            s.close();
        }

        void insert(String destination, String surname, String name, String phone) {    //The insert function is just a test, needs to be implemented
            try {
                String familyName = "'"+surname+"'";
                String givenName = "'"+name+"'";
                String phoneNumber = "'"+phone+"'";
                String sqlStr = new StringBuilder().append("INSERT INTO ").append(destination).append(" (familyname, givenname, phonenumber) values (")
                        .append(familyName).append(", ").append(givenName).append(", ").append(phoneNumber).append(");").toString();
                Statement s = conn.createStatement();
                s.execute(sqlStr);
                s.close();
                //conn.close();
            } catch (Exception e) {
            }

        }

        void delete(String table) throws SQLException {
            String sqlStr = new StringBuilder().append("DROP TABLE ").append(table).append(";").toString();
            Statement s = conn.createStatement();
            s.execute(sqlStr);
            s.close();
            //conn.close();
        }

        void closeConnect() throws SQLException {
            conn.close();
        }
    }
