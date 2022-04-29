package answer01;

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
        out.println("Insert Table name ?");
        Scanner sc = new Scanner(in);
              String tablename = sc.next();

        try {
            Statement st = r.conenction.createStatement();
            ResultSet rs = st.executeQuery("select * from "+tablename);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                out.println("Table Name : "+rsmd.getTableName(i)+"\t");
                out.print(rsmd.getColumnName(i)+"\t");
                out.print(rsmd.getColumnTypeName(i)+"\t");
            }
            while (rs.next())
            {
                String name = rs.getString(1);
                String price = rs.getString(2);
                out.println("name : "+name+" price : "+price);
            }

          } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
