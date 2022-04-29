package sample01;

import java.sql.*;

import static java.lang.System.out;
public class storedProcess {
    Connection conenction;

    public storedProcess() throws SQLException {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {
              storedProcess r = new storedProcess();
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
