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
            boolean b = statement.execute(query);
            if (b){
                msg ="Delete Record Success !";
            }else{
                msg ="Delete Record Error !";
            }
        } catch (SQLException e) {
            msg = "Delete Record Failed : "+e.getMessage();
        }
        return msg;
    }
}