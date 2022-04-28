package sample04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;
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
            PreparedStatement ps = r.conenction.prepareStatement("insert into employee_details values(?,?,?,?,?)");
   BufferedReader reader = new BufferedReader
                    (new FileReader("E:\\vProject\\Projects\\Java-Day14\\src\\main\\java\\sample04\\employee.csv"));

       String line = "";
       while ((line=reader.readLine())!=null){
           String[] arr = line.split(",");

           ps.setInt(1,Integer.parseInt(arr[1]));//id
           ps.setString(2,arr[0]);//name
           ps.setString(3,arr[2]);//dep
           ps.setDouble(4,Double.parseDouble(arr[3]));//sal
           ps.setString(5,arr[4]);//desig
           ps.addBatch();
       }
       int[] batches = ps.executeBatch();
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
