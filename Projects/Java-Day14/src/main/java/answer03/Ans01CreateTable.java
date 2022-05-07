package answer03;

import java.sql.*;

public class Ans01CreateTable {
   private Connection conenction;

    public Ans01CreateTable()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans01CreateTable r = new Ans01CreateTable();
              r.execute();
    }

    private void execute() {

        try {
            conenction.createStatement().execute("CREATE TABLE bank (\n" +
                    "  accno varchar(45) DEFAULT NULL,\n" +
                    "  balance double DEFAULT NULL,\n" +
                    "  username varchar(255) NOT NULL,\n" +
                    "  password varchar(45) DEFAULT NULL)");
        } catch (SQLException e) {
            System.out.println("Error : "+e.getMessage());
        }


    }

}
