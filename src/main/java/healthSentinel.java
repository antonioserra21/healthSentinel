import java.io.IOException;
import java.sql.SQLException;

public class healthSentinel {
    public static void main(String[] args) throws SQLException, IOException {
        myDB db = new myDB();
        boolean run = true;
        db.connect("insertUsername", "insertPassword" );
        db.createPatientsTable();
        while (run) {
            run = db.client();
        }
        db.deletePatientsTable();
        db.closeConnect();
    }
}
