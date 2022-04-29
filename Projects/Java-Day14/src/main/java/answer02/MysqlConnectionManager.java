package answer02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnectionManager {


    private static Connection connection;

   private static final String username = "root";
    private static final String password = "123";

    public MysqlConnectionManager(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){

        if(connection==null){
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",username,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
