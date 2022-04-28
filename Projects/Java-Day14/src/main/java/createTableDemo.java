import java.sql.*;

public class createTableDemo {



    public createTableDemo(){
    }

    public static void main(String[] args) throws SQLException {


        Connection conenction = MysqlConnectionManager.getConnection();

          PreparedStatement stm2 = conenction.
                  prepareStatement("insert into Users(id,first_name,last_name,email) values(?,?,?,?)");
         Users u1 = new Users("janith","chandra","test@test.com");
        genSQLAndExecute(stm2,u1,2);
        System.out.println("saved Successfully !");
    }

    private static void genSQLAndExecute(PreparedStatement stmt, Users u1,int id) {

        try {
            stmt.setInt(1,id);
            stmt.setString(2,u1.getFirstName());
            stmt.setString(3,u1.getLastName());
            stmt.setString(4,u1.getEmail());
            int x = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
