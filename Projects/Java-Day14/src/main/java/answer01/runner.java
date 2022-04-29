package answer01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;
public class runner {
    Connection conenction;

    public runner() throws SQLException {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {
              runner r = new runner();


        try {
                   r.conenction.setAutoCommit(false);
                   //"insert into employee_details values(?,?,?,?,?)"
            Statement statement = r.conenction.createStatement();
   BufferedReader reader = new BufferedReader
                    (new FileReader("E:\\vProject\\Projects\\Java-Day14\\src\\main\\java\\sample04\\employee.csv"));
       String line = "";
       while ((line=reader.readLine())!=null){
           String[] arr = line.split(",");
           statement.addBatch("insert into employee_details values('"+
                   Integer.parseInt(arr[1])+"','"+arr[0]+"','"+arr[2]+
                   "','"+Double.parseDouble(arr[3])+"','"+arr[4]+"')");
       }
       int[] batches = statement.executeBatch();
            for (int i = 0; i < batches.length; i++) {
                out.println("batch id : "+batches[i]);
            }
            r.conenction.commit();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
