package sample04;

import java.sql.Connection;
import java.sql.SQLException;

public class Ans05AlterTable {
   private Connection conenction;

    public Ans05AlterTable()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans05AlterTable r = new Ans05AlterTable();
              r.execute();
    }

    private void execute() {
                        // add new column
        try {
            boolean b = conenction.createStatement().execute("ALTER TABLE bank \n" +
                    "ADD COLUMN extra VARCHAR(45)");
            System.out.println("Alter Table Bank Successfully");

            //uncomment to drop column
//            int i2 = conenction.createStatement().executeUpdate("ALTER TABLE bank \n" +
//                    "drop COLUMN extra;");
//            if(i2>0){
//                System.out.println("add new column Successfully !");
//            }else{
//                System.out.println("Error !");
//            }
        } catch (SQLException e) {
            System.out.println("Error : "+e.getMessage());
        }


    }

}
