package answer03;

import java.sql.*;

public class Ans20leftjoin {
   private Connection conenction;

    public Ans20leftjoin()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans20leftjoin r = new Ans20leftjoin();
              r.execute();
    }
    private void execute() {
        // left join  keyword
        try {
           Statement stmt =  conenction.createStatement();
           String String = executeSql(stmt,"SELECT * from users u left join employee_details e on u.first_name = e.name" +
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
