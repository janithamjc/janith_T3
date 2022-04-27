package sample01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlDemo {

    public static void main(String[] args) {

        try {
            Connection conn = MysqlConnectionManager.getConnection();
           ResultSet rs =conn.createStatement().
                   executeQuery("select * from users");
            while (rs.next()){
                int id = rs.getInt("idUser");
                String name = rs.getString("name");
                System.out.println(id+" "+name);
            }
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
