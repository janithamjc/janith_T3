import org.hibernate.Session;
import org.hibernate.Transaction;
import orm.Employee;

public class Runner {


    public static void main(String[] args) {
        Employee employee = new Employee("Janith", "Chandra", "janith@gmail.com");
        Employee employee1 = new Employee("Suranga", "Perera", "suranga@gmail.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(employee);
            session.save(employee1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
