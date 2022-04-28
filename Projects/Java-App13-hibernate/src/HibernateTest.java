

import java.util.Properties;

public class HibernateTest  {

        public static void main(String arg[]) {
            Properties prop= new Properties();

            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");

            //You can use any database you want, I had it configured for Postgres
            prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            prop.setProperty("hibernate.connection.username", "root");
            prop.setProperty("hibernate.connection.password", "123");
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            prop.setProperty("show_sql", "true");
            prop.setProperty("hibernate.ddl-auto", "create");



        }

    }

