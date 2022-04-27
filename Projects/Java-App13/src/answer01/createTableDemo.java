package answer01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class createTableDemo {



    public createTableDemo(){
    }

    public static void main(String[] args) throws SQLException {


        Connection conenction = MysqlConnectionManager.getConnection();
        Statement stmt = conenction.createStatement();

        Book b1 = new Book("book 01","vijaya","unknown");
        Book b2 = new Book("book 02","Sarath","malith");
        Book b3 = new Book("book 03","Kasun","janith");


    }

    private static void genSQLAndExecute(Statement stmt, Book b3) {
        String sql = "insert into book(name,publication,author) " +
                "values('"+b3.getBookName()+"','"+b3.getPublication()+"','"+b3.getAuthor()+"')";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
