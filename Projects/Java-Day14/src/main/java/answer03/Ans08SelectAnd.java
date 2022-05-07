package answer03;

import java.sql.*;

public class Ans08SelectAnd {
   private Connection conenction;

    public Ans08SelectAnd()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans08SelectAnd r = new Ans08SelectAnd();
              r.execute();
    }
    private void execute() {
        // And keyword with operators
        try {
           Statement stmt =  conenction.createStatement();

           String String = executeSql(stmt,"select * department from employee_details " +
                   " where balance > 1000 and balance <2000 ");
            System.out.println(String);
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
