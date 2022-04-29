package answer02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;
public class runner {
   private Connection conenction;

    public runner() throws SQLException {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {
              runner r = new runner();


        try {
            PreparedStatement ps = r.conenction.prepareStatement("select * from employee_details",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = ps.executeQuery();
            out.println("~going fwd~");
            while (rs.next()){
                int empid = rs.getInt(1);
                String empName = rs.getString(2);
                String empDep = rs.getString(3);
                String empSal = rs.getString(4);
                String empDesig = rs.getString(5);
                out.println("Emp Id: "+empid+"\tEmp Name: "+empName+"\tEmp Dep: "+empDep+"\tEmp Sal: "+empSal+"\tDesignation:"+empDesig);
            }
            out.println("~going back~");

            while (rs.previous()){
                int empid = rs.getInt(1);
                String empName = rs.getString(2);
                String empDep = rs.getString(3);
                String empSal = rs.getString(4);
                String empDesig = rs.getString(5);
                out.println("Emp Id: "+empid+"\tEmp Name: "+empName+"\tEmp Dep: "+empDep+"\tEmp Sal: "+empSal+"\tDesignation:"+empDesig);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
