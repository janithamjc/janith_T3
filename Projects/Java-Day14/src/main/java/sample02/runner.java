package sample02;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.*;
public class runner {
    Connection conenction;

    public runner(){
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {

        runner r = new runner();
        r.saveUser();
//        r.login();

    }

    private void login() {
        Scanner sc = new Scanner(in);
        out.println("Insert UserName ?");
        String username = sc.next();
        out.println("Insert Password ?");
        String password = sc.next();

        PreparedStatement stm2 = null;
        try {
            stm2 = conenction.prepareStatement("select * from user_details" +
                    " where username = ? and password = ?");
            stm2.setString(1,username);
            stm2.setString(2,password);
            ResultSet rs = stm2.executeQuery();
            if (rs.next()){
                out.println("User Found !");
                out.println(rs.getString(1)+"\t "+
                        rs.getString(2)+"\t"+
                        rs.getString(3)+"\t"+
                        rs.getString(4)+"\t"+
                        rs.getString(5));
            }else{
                out.println("User Not Found !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private void saveUser(){
//collect data
        Scanner sc = new Scanner(in);
        out.println("Insert UserName ?");
        String username = sc.next();
        out.println("Insert Password ?");
        String password = sc.next();
        out.println("Insert email ?");
        String email = sc.next();
        out.println("Insert First Name ?");
        String fName = sc.next();
        out.println("Insert Last Name ?");
        String lName = sc.next();
        Users u1 = new Users();
        u1.setUsername(username); u1.setPassword(password);
        u1.setFirstName(fName);u1.setLastName(lName);u1.setEmail(email);
        PreparedStatement stm2 = null;
        try {
            stm2 = conenction.prepareStatement("insert into user_details" +
                            "(fname,lname,email,username,password) values(?,?,?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        genSQLAndExecute(stm2,u1);
    }

    private static void genSQLAndExecute(PreparedStatement stmt, Users u1) {

        try {
            stmt.setString(1,u1.getFirstName());
            stmt.setString(2,u1.getLastName());
            stmt.setString(3,u1.getEmail());
            stmt.setString(4,u1.getUsername());
            stmt.setString(5,u1.getPassword());
            int x = stmt.executeUpdate();
            out.println("User Saved Successfully !");
        }catch (SQLIntegrityConstraintViolationException w){
            out.println("email / username Cannot duplicate : "+w.getMessage());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
