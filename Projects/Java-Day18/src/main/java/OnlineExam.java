import java.util.List;
import java.util.Map;

public class OnlineExam {
    int id;
    String name;
    Map<String, List<Question>> questions;

    public OnlineExam() {
    }

    public OnlineExam(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public OnlineExam(int id, String name, Map<String, List<Question>> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<Question>> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, List<Question>> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "OnlineExam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
