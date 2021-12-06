import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

class myDB {
    String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    Connection conn = null;

    //connects java program with the SQL database
    void connect(String user, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }
        conn = DriverManager.getConnection(dbUrl, user, password);
    }

    //closes the java-database connection
    void closeConnect() throws SQLException {
        conn.close();
    }

    //Creates a table in the database
    void createPatientsTable() throws SQLException {
        String sqlStr = "create table patients ( id SERIAL PRIMARY KEY, name varchar(20) NOT NULL, surname varchar(20) NOT NULL, sex varchar(20), age varchar(20));";
        Statement s = conn.createStatement();
        s.execute(sqlStr);
        s.close();
    }

    //deletes table
    void deletePatientsTable() throws SQLException {
        String sqlStr = "drop table patients;";
        Statement s = conn.createStatement();
        s.execute(sqlStr);
        s.close();
    }

    private void assign(String name, String surname, String sex, String age, String sqlStr) throws SQLException {
        PreparedStatement prpStm = conn.prepareStatement(sqlStr);
        prpStm.setString(1, sex);
        prpStm.setString(2, age);
        prpStm.setString(3, name);
        prpStm.setString(4, surname);
        prpStm.executeUpdate();
        prpStm.close();
    }

    //insert patient in patient table
    void insertPatient(String name, String surname, String sex, String age) {    //The insert function is just a test, needs to be implemented
        try {
            String sqlStr = "insert into patients ( name, surname, sex, age) values (?, ?, ?, ?);";
            assign(sex, age, name, surname, sqlStr);
        } catch (Exception e) {
        }
    }

    //remove patient form the patient table
    void removePatient(String name, String surname) throws SQLException {
        try {
            String sqlStr = "delete from patients where name = ? and surname = ?;";
            PreparedStatement prpStm = conn.prepareStatement(sqlStr);
            prpStm.setString(1, name);
            prpStm.setString(2, surname);
            prpStm.executeUpdate();
            prpStm.close();
        } catch (Exception e) {
        }
    }

    //Extract of a patient from the patient table
    void selectPatient(String name, String surname) throws SQLException {
        try {
            String sqlStr = "select * from patients where name = ? and surname = ?;";
            PreparedStatement prpStm = conn.prepareStatement(sqlStr);
            prpStm.setString(1, name);
            prpStm.setString(2, surname);
            ResultSet rs = prpStm.executeQuery();
            prpStm.close();
            while (rs.next()) {
                //Display values
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Surname: " + rs.getString("surname"));
                System.out.println("Sex: " + rs.getString("sex"));
                System.out.println("Age: " + rs.getString("age"));
            }
        } catch (Exception e) {
        }
    }


    void updatePatient(String name, String surname, String sex, String age) throws SQLException {
        try {
            String sqlStr = "update patients set sex = ?, age = ? where name = ? and surname = ?;";
            assign(name, surname, sex, age, sqlStr);
        } catch (Exception e) {
        }
    }

    boolean client() throws IOException, SQLException {
        System.out.println("-------------------------------------");
        System.out.println("Welcome to Health Sentinel Database Client.\n");
        System.out.println("Select one on the following option:");
        System.out.println("1) Insert patient");
        System.out.println("2) Remove patient");
        System.out.println("3) Select patient");
        System.out.println("4) Update patient\n");
        Scanner sc = new Scanner(System.in);
        String name, surname, sex, age;
        boolean status = true;
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice){
            case 1:
                System.out.print("Insert name: ");
                name = sc.nextLine();
                System.out.print("Insert surname: ");
                surname = sc.nextLine();
                System.out.print("Insert sex: ");
                sex = sc.nextLine();
                System.out.print("Insert age: ");
                age = sc.nextLine();
                insertPatient(name, surname, sex, age);
                break;
            case 2:
                System.out.print("Insert name: ");
                name = sc.nextLine();
                System.out.print("Insert surname: ");
                surname = sc.nextLine();
                removePatient(name, surname);
                break;
            case 3:
                System.out.print("Insert name: ");
                name = sc.nextLine();
                System.out.print("Insert surname: ");
                surname = sc.nextLine();
                selectPatient(name, surname);
                break;
            case 4:
                System.out.print("Insert name: ");
                name = sc.nextLine();
                System.out.print("Insert surname: ");
                surname = sc.nextLine();
                System.out.print("Insert new value for sex: ");
                sex = sc.nextLine();
                System.out.print("Insert new value for age: ");
                age = sc.nextLine();
                updatePatient(name, surname, sex, age);
                break;
            default:
                status = false;
        }return status;
    }
}
