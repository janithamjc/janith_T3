import querypack.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private Connection conn;

    Main(){
        conn = MysqlConnectionManager.getConnection();
    }

    public static void main(String[] args) {
        Main m = new Main();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Insert Your MySQL Query !");
            String data = scanner.nextLine().toLowerCase();
            System.out.println("input>" + data);
            try {
                String result = m.processData(data, m.conn.createStatement());
                System.out.println(result);
            } catch (SQLException e) {
                System.out.println("Connection creation Error: " + e);
            }
        }

    }

    private String processData(String query, Statement statement) {
        QueryProcessor qp = null;

        if(query.contains("select")){
            qp = new SelectProcessor(statement);
        }
        else if(query.contains("update")){
            qp = new UpdateProcessor(statement);
        }
        else if(query.contains("delete")){
            qp = new DeleteProcessor(statement);
        }
        else if(query.contains("insert")){
            qp = new InsertProcessor(statement);
        }else {
            return "Incorrect Statement.";
        }
            return qp.executeSql(query);
    }


}
