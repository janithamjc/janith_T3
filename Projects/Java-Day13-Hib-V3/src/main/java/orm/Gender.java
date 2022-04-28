package orm;

import javax.persistence.*;

@Entity
@Table(name = "Gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "gender")
    private String gender;

    public Gender() {

    }

    public Gender(int id, String gender) {
        this.id = id;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Users [id=" + id + ",Gender="+gender+ "]";
    }
}