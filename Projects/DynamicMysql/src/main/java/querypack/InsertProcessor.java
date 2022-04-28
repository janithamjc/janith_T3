package querypack;
import java.sql.*;

public class InsertProcessor implements QueryProcessor{
    Statement statement;
    public InsertProcessor(Statement stmt){
        this.statement = stmt;
    }
    @Override
    public String executeSql(String query) {
        String msg ="";
        try {
              statement.execute(query);
               int count =  statement.getUpdateCount();
                msg ="Record Insert Success.: "+count;

        } catch (SQLException e) {
           msg = "Insert Record Failed : "+e.getMessage();
        }
        return msg;
    }
}