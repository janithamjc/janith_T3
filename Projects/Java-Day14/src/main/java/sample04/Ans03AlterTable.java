package sample04;

import java.sql.Connection;
import java.sql.SQLException;

public class Ans03AlterTable {
   private Connection conenction;

    public Ans03AlterTable()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans03AlterTable r = new Ans03AlterTable();
              r.execute();
    }

    private void execute() {
                        // add Primary Column
        try {
            int i = conenction.createStatement().executeUpdate("ALTER TABLE bank  \n" +
                    "ADD PRIMARY KEY(accno);\n");
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
