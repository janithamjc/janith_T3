package sample03;

public class Bank {


    private String accNumber;



    private double balance;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public Bank(String accNumber, String lastName, double balance, String username, String password) {
        this.accNumber = accNumber;
        this.balance = balance;
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bank() {

    }

    public Bank(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }


    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "sample01.Users [ firstName=" + accNumber + ", balance=" + balance + "]";
    }
}