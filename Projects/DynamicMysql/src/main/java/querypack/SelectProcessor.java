package querypack;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.IntStream;

public class SelectProcessor implements QueryProcessor{
    Statement statement;
    public SelectProcessor(Statement stmt){
        this.statement = stmt;
    }
    @Override
    public String executeSql(String query) {
        String msg ="";
        try {
            System.out.println("select : "+query);
            ResultSet rs = statement.executeQuery(query);

             ResultSetMetaData rsm = rs.getMetaData();
             int columnCount = rsm.getColumnCount();

            for (int i = 1; i < columnCount+1; i++) {
                msg = msg +rsm.getColumnName(i).toUpperCase()+"\t";
            }
            while (rs.next()){
                msg = msg+"\n";
                for (int i = 1; i < columnCount+1; i++) {
                    msg = msg +rs.getString(i)+"\t";
                }
            }
        } catch (SQLException e) {
            msg = "Select Records Failed : "+e.getMessage();
        }
        return msg;
    }
}