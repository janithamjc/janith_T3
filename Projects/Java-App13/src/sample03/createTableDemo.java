package sample03;

import java.sql.*;

public class createTableDemo {



    public createTableDemo(){
    }

    public static void main(String[] args) throws SQLException {


        Connection conenction = MysqlConnectionManager.getConnection();
        String sql = "insert into book(name,publication,author) " +
                "values(?,?,?)";
        PreparedStatement stmt = conenction.prepareStatement(sql);
        String query = "create table Book(" +
                "bookid integer not null auto_increment," +
                "name varchar(255)," +
                "publication varchar(255)," +
                "author varchar(255)," +
                "primary key (bookid)" +
                ")";

      //  int created = stmt.executeUpdate(query);

//        System.out.println("table created successfully !"+created);

        Book b1 = new Book("book 01","vijaya","unknown");
        Book b2 = new Book("book 02","Sarath","malith");
        Book b3 = new Book("book 03","Kasun","janith");

        genSQLAndExecute(stmt,b1);
        genSQLAndExecute(stmt,b2);
        genSQLAndExecute(stmt,b3);

        //   Statement stm2 = conenction.createStatement();
          Statement stm2 = conenction.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stm2.executeQuery("select * from Book");

        System.out.println("ID\tName\tPublication\tAuthor");
        System.out.println("---------------fwd-----------------------");
        while (rs.next()){
            System.out.println(rs.getInt(1)+"\t"+
                    rs.getString(2)+"\t"+rs.getString(3)+"\t"
                    +rs.getString(4));
        }



    }

    private static void genSQLAndExecute(PreparedStatement stmt, Book b3) {


        try {
            stmt.setString(1, b3.getBookName());
            stmt.setString(2, b3.getPublication());
            stmt.setString(3, b3.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


}
