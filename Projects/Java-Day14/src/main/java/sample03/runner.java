package sample03;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
public class runner {
    Connection conenction;

    public runner() throws SQLException {
        conenction = MysqlConnectionManager.getConnection();
    }
    public static void main(String[] args) throws SQLException {
        runner r = new runner();
//        r.saveAccount();
        r.updateAcc();
    }

    private void login() {
        Scanner sc = new Scanner(in);
        out.println("Insert UserName ?");
        String username = sc.next();
        out.println("Insert Password ?");
        String password = sc.next();

        PreparedStatement stm2 = null;
        try {
            stm2 = conenction.prepareStatement("select * from bank " +
                    " where username = ? and password = ?");
            stm2.setString(1,username);
            stm2.setString(2,password);
            ResultSet rs = stm2.executeQuery();
            if (rs.next()){
                out.println("Account Found, Details  : ");
                out.println("Account Number : "+rs.getString(1)+
                        "\nBalance : "+rs.getString(2)+
                        "\nUsername : "+rs.getString(3)+
                        "\nPassword : "+rs.getString(4) );

            }else{
                out.println("Account Not Found !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    private void updateAcc() {
        Scanner sc = new Scanner(in);
        out.println("Insert UserName ?");
        String username = sc.next();
        out.println("Insert Password ?");
        String password = sc.next();

        PreparedStatement stm2 = null;
        try {
            stm2 = conenction.prepareStatement("select * from bank " +
                    " where username = ? and password = ?");
            stm2.setString(1,username);
            stm2.setString(2,password);
            ResultSet rs = stm2.executeQuery();
            if (rs.next()){
                out.println("Account Found, Details  : ");
                String accNo = rs.getString(1);
                double balance = rs.getDouble(2);
                String userName = rs.getString(3);

                out.println("~~~Transfer Money~~~");
                out.println("To Account Number ? ");
                String recAccount = sc.next();
                out.println("Transfer Amount ? ");
                double amu = sc.nextDouble();

                if(amu>balance){
                    out.println("~~~No Sufficient Balance~~~");
                }else{
                    conenction.setAutoCommit(false);
                    try {
                        //deduct
                        PreparedStatement pst1 = conenction.prepareStatement("update bank set balance = ? where accno = ? ");
                        pst1.setDouble(1,balance-amu);
                        pst1.setString(2,accNo);
                        pst1.execute();
                        //insert
                        PreparedStatement pst2 = conenction.prepareStatement("update bank set balance = (balance + ?) where accno = ? ");
                        pst2.setDouble(1, amu);
                        pst2.setString(2,recAccount);
                        pst2.execute();
                        conenction.commit();

                        //show updated val
                        PreparedStatement pst3 = conenction.prepareStatement("select accno,balance from bank where accno = ? or accno = ? ");
                        pst3.setString(1,accNo);
                        pst3.setString(2,recAccount);

                        out.println("updated Data given below,");
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()){
                            String acc = rs3.getString(1);
                            double bal = rs3.getDouble(2);
                            out.println("Acc: "+acc+" Balance : "+bal);
                        }

                    }catch (Exception e){
                        conenction.rollback();
                        e.printStackTrace();
                    }



                }

            }else{
                out.println("Account Not Found !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private void saveAccount(){
//collect data
        Scanner sc = new Scanner(in);
        out.println("Insert UserName ?");
        String username = sc.next();
        out.println("Insert Password ?");
        String password = sc.next();
        out.println("Insert account balance ?");
        double balance = sc.nextDouble();
        out.println("Insert account Number ?");
        String accNum = sc.next();
        Bank u1 = new Bank();
        u1.setUsername(username); u1.setPassword(password);
        u1.setAccNumber(accNum);u1.setBalance(balance);
        PreparedStatement stm2 = null;
        try {
            stm2 = conenction.prepareStatement("insert into bank" +
                            "(accno,balance,username,password) values(?,?,?,?)");

            genSQLAndExecute(stm2,u1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void genSQLAndExecute(PreparedStatement stmt, Bank u1) throws SQLException {

        try {
            stmt.setString(1,u1.getAccNumber());
            stmt.setDouble(2,u1.getBalance());
            stmt.setString(3,u1.getUsername());
            stmt.setString(4,u1.getPassword());
            int x = stmt.executeUpdate();
            out.println("Bank Saved Successfully !");
            stmt.getConnection().commit();
        }catch (SQLIntegrityConstraintViolationException w){
            out.println("username Cannot duplicate : "+w.getMessage());
        }
        catch (SQLException e) {
            stmt.getConnection().rollback();
            throw new RuntimeException(e);
        }


    }


}
