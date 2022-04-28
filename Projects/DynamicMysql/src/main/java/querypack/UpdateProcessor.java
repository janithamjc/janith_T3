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
        String msg ="";
        try {
            statement.execute(query);
            int count =  statement.getUpdateCount();
            msg ="Update Record Success.: "+count;

        } catch (SQLException e) {
            msg = "Update Record Failed : "+e.getMessage();
        }
        return msg;
    }
}