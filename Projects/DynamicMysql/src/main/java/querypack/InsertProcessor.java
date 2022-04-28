package querypack;
import java.sql.SQLException;
import java.sql.Statement;
public class InsertProcessor implements QueryProcessor{
    Statement statement;
    public InsertProcessor(Statement stmt){
        this.statement = stmt;
    }
    @Override
    public String executeSql(String query) {
        String msg ="";
        try {
            boolean b = statement.execute(query);
            if (b){
                msg ="Record Insert Success !";
            }else{
                msg ="Record Insert Error !";
            }
        } catch (SQLException e) {
           msg = "Insert Record Failed : "+e.getMessage();
        }
        return null;
    }
}