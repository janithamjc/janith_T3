package answer03;

import java.sql.*;

public class Ans15SelectgroupAvg {
   private Connection conenction;

    public Ans15SelectgroupAvg()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans15SelectgroupAvg r = new Ans15SelectgroupAvg();
              r.execute();
    }
    private void execute() {
        //  Avg keyword
        try {
           Statement stmt =  conenction.createStatement();
           String String = executeSql(stmt,"select name,department,avg(salary) as `Avg Salary`  from employee_details " +
                   "  ");
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
