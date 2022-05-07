package answer03;

import java.sql.Connection;
import java.sql.SQLException;

public class Ans04AlterTable {
   private Connection conenction;

    public Ans04AlterTable()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans04AlterTable r = new Ans04AlterTable();
              r.execute();
    }

    private void execute() {
                        // add Primary Column
        try {
            int i = conenction.createStatement().executeUpdate("ALTER TABLE bank " +
                    "DROP COLUMN username");
            if(i>0){
                System.out.println("Primary Key applied Successfully !");
            }else{
                System.out.println("Error !");
            }
        } catch (SQLException e) {
            System.out.println("Error : "+e.getMessage());
        }


    }

}
