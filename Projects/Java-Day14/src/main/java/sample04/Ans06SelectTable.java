package sample04;

import java.sql.*;

public class Ans06SelectTable {
   private Connection conenction;

    public Ans06SelectTable()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans06SelectTable r = new Ans06SelectTable();
              r.execute();
    }
    private void execute() {
        // simple select with where
        try {
           Statement stmt =  conenction.createStatement();
           String Query = "";
            executeSql(stmt,"select * from employee_details");
        } catch (SQLException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }
    public String executeSql(Statement statement, String query) {
        String msg ="";
        try {
            System.out.println("select : "+query);
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsm = rs.getMetaData();
            int columnCount = rsm.getColumnCount();
            for (int i = 1; i < columnCount+1; i++) {
                msg = msg +rsm.getColumnName(i).toUpperCase()+"\t";
            }
            while (rs.next()){
                msg = msg+"\n";
                for (int i = 1; i < columnCount+1; i++) {
                    msg = msg +rs.getString(i)+"\t";
                }
            }
        } catch (SQLException e) {
            msg = "Select Records Failed : "+e.getMessage();
        }
        return msg;
    }

}
