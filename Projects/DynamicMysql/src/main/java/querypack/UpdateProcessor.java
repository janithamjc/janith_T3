package querypack;

import java.sql.SQLException;
import java.sql.Statement;

public class UpdateProcessor  implements QueryProcessor{
    Statement statement;
    public UpdateProcessor(Statement stmt){
        this.statement = stmt;
    }

    @Override
    public String executeSql(String query) {
        String msg = "";
        try {
            int i = statement.executeUpdate(query);
            msg = "Update Record Success !";
        } catch (SQLException e) {
            System.out.println("Insert Record Failed : "+e.getMessage());;
            msg = "Update Error: "+e.getMessage();
        }
        return msg;
    }
}
