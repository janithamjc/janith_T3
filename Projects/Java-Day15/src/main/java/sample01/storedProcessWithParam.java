package sample01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class storedProcessWithParam {
    Connection conenction;

    public storedProcessWithParam() throws SQLException {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {
              storedProcessWithParam r = new storedProcessWithParam();
        try {
           CallableStatement cs =  r.conenction.prepareCall("call ");
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next())
            {
                String name = rs.getString("");
                String price = rs.getString("");
                out.println("name : "+name+" price : "+price);
            }

          } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
