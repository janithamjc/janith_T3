package answer02;

import sample01.MysqlConnectionManager;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class MetaDetaDemo {
    Connection conenction;

    public MetaDetaDemo() throws SQLException {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {
              MetaDetaDemo r = new MetaDetaDemo();
        out.println("Insert Database name ?");
        Scanner sc = new Scanner(in);
              String tablename = sc.next();

        try {
            Statement st = r.conenction.createStatement();
            ResultSet rs = st.executeQuery("select * from "+tablename);
            DatabaseMetaData rsmd = r.conenction.getMetaData();

          } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
