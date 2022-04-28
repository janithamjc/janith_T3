package querypack;

import java.sql.SQLException;
import java.sql.Statement;

public class DeleteProcessor implements QueryProcessor{
    Statement statement;
    public DeleteProcessor(Statement stmt){
        this.statement = stmt;
    }
    @Override
    public String executeSql(String query) {
        String msg ="";
        try {
           int count = statement.executeUpdate(query);
            msg ="Record Delete Success.: "+count;

        } catch (SQLException e) {
            msg = " Delete Failed : "+e.getMessage();
        }
        return msg;
    }
}