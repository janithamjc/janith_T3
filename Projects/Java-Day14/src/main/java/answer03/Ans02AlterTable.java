package answer03;

import java.sql.Connection;
import java.sql.SQLException;

public class Ans02AlterTable {
   private Connection conenction;

    public Ans02AlterTable()  {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args)  {
              Ans02AlterTable r = new Ans02AlterTable();
              r.execute();
    }

    private void execute() {
                        // add new column
        try {
            int i = conenction.createStatement().executeUpdate("ALTER TABLE bank  \n" +
                    "ADD COLUMN account_index INT NULL AFTER password;\n");
            if(i>0){
                System.out.println("add new Column Successfully !");
            }else{
                System.out.println("Error !");
            }
        } catch (SQLException e) {
            System.out.println("Error : "+e.getMessage());
        }


    }

}
