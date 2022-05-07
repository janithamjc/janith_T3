import java.util.List;
import java.util.Map;

public class Question {
    int id;
    String qName;
   List<Option> optionList;

    public Question() {
    }

    public Question(int id, String qName, List<Option> optionList) {
        this.id = id;
        this.qName = qName;
        this.optionList = optionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", qName='" + qName + '\'' +
                ", optionList=" + optionList +
                '}';
    }
}
