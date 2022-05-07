package answer03;

import java.sql.*;

public class Ans18View {
   private Connection conenction;

    public Ans18View()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans18View r = new Ans18View();
              r.execute();
    }
    private void execute() {
        //  view keyword
        try {
           Statement stmt =  conenction.createStatement();
           String String = executeSql(stmt,"CREATE VIEW  worker_sal " +
                   "AS (SELECT empid,name,salary  FROM test.employee_details where desig = 'Worker')" +
                   "  ");

           //get view table data
            Statement stm2 =  conenction.createStatement();
            String String2 = executeSql(stmt,"CREATE VIEW  worker_sal");


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
