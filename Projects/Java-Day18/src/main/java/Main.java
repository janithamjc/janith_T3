import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext5.xml");
        String arr[] = context.getBeanDefinitionNames();
        for(String a:arr){
            System.out.println("Name of the Bean : "+a);
        }

        System.out.println("~~~~~~Exams~~~~~~~`");
        OnlineExam e1 = context.getBean("exam", OnlineExam.class);
        System.out.println("Exam Id   : "+e1.getId());
        System.out.println("Set Name : "+e1.getName());
        Map<String, List<Question>> qList = e1.getQuestions();

        Set<String> keys = qList.keySet();
        for (String k: keys){
            List<Question> Quiz =  qList.get(k);
             for (Question q : Quiz){
                 System.out.println("Question#"+q.id+">"+q.qName);
                    for (Option ops: q.optionList){
                        System.out.println("Option#"+ops.optionId+":"+ops.getOptionValue());
                    }
             }
        }

    }
}
