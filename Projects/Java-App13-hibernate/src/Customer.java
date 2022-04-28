
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "customerid")})
public class Customer implements Serializable {

    private String name;
    private int customerid;
    private boolean isActive;

    public Customer() {
    }

    public Customer(String name, int customerId, boolean isActive) {
        this.name = name;
        this.customerid = customerId;
        this.isActive = isActive;
    }

    /**
     *      GETTERS
     */

    @Column(name = "name", unique = false, nullable = false, length = 100)
    public String getname() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid", unique = true, nullable = false)
    public int getcustomerid() {
        return customerid;
    }

    @Column(name = "isactive", unique = false, nullable = false)
    public boolean getisactive() {
        return isActive;
    }


    /**
     *      SETTERS
     */
    public void setname(String name) {
        this.name = name;
    }

    public void setisactive(boolean isActive) {
        this.isActive = isActive;
    }
}