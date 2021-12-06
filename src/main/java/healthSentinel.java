import java.sql.SQLException;

public class healthSentinel {
    public static void main(String[] args) throws SQLException {
        myDB db = new myDB();
        db.connect("postgres", "Liberanosamalo" );
        //db.delete("patients");
        //db.createTable("patients");
        db.closeConnect();
    }
}
