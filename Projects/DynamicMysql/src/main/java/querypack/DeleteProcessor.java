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
            statement.execute(query);
            int count =  statement.getUpdateCount();
            msg ="Record Delete Success.: "+count;

        } catch (SQLException e) {
            msg = " Delete Failed : "+e.getMessage();
        }
        return msg;
    }
}