package com.green.testquiz.datalayer.mocks;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import org.bson.types.ObjectId;

import java.util.*;

/**
 * Created by medvedevyakov on 2019-12-10.
 */
public class MockQuizList {

    private static Quiz QUIZ;
    private static Set<Question> QUESTIONS = new HashSet<>();
    public static final List<Quiz> MOCK_QUIZ_LIST = new ArrayList<>();

    static {
        Set<Option> options1 = new HashSet<>();
        Option o1 = new Option(new ObjectId("q1o1"), "updateState()", false , false);
        Option o2 = new Option(new ObjectId("q1o2"), "changeState()", false, false);
        Option o3 = new Option(new ObjectId("q1o3"), "setState()", true, false);
        Option o4 = new Option(new ObjectId("q1o4"), "putState()", false, false);
        options1.add(o1);
        options1.add(o2);
        options1.add(o3);
        options1.add(o4);
        Question q1 = new Question(new ObjectId("q1"), "Method to change state",
                "Which method call causes a component to change state?", "",
                QuestionType.ONE_CHOICE, options1);

        Set<Option> options2 = new HashSet<>();
        Option o21 =  new Option(new ObjectId("q2o1"), "Yes", true,false);
        Option o22 = new Option(new ObjectId("q2o2"), "No", false,false);
        Option o23 =  new Option(new ObjectId("q2o3"), "Maybe", false, false);
        options2.add(o21);
        options2.add(o22);
        options2.add(o23);
        Question q2 = new Question( new ObjectId("q2"), "Stateless Functional Components",
                "Does Stateless Functional Components in React.js invoke life cycle methods?",
                "", QuestionType.ONE_CHOICE, options2);


        Set<Option> options3 = new HashSet<>();
        Option o31 =   new Option(new ObjectId("q3o1"),
                "Yes, if you pass the ref value for the child component to props and get to the element through a chain of refs",
                true,false);
        Option o32 =  new Option(new ObjectId("q3o2"),
                "No, it is not possible to access the elements of the child component, since this is contrary to the philosophy of React.js",
                false, false);
        Option o33 = new Option(new ObjectId("q3o3"), "Yes, you can access using the this keyword", false, false);

        options3.add(o31);
        options3.add(o32);
        options3.add(o33);
        Question q3 = new Question( new ObjectId("q3"), "Child component access",
                "Is it possible to access the value of the element of the child component through the ref of this element?",
                "", QuestionType.ONE_CHOICE, options3);

        Set<Option> options4 = new HashSet<>();
        Option o41 = new Option(new ObjectId("q4o1"), "Yes", false,false);
        Option o42 = new Option(new ObjectId("q4o2"), "No", true,false);
        Option o43 = new Option(new ObjectId("q4o3"), "Sometimes", false,false);
        options4.add(o41);
        options4.add(o42);
        options4.add(o43);
        Question q4 = new Question( new ObjectId("q4"), "Using the setState()",
                "Is using the setState () method acceptable in componentDidMount()?",
                "", QuestionType.ONE_CHOICE, options4);

        Set<Option> options5 = new HashSet<>();
        Option o51 = new Option(new ObjectId("q5o1"), "Shadow DOM", false, false);
        Option o52 = new Option(new ObjectId("q5o2"), "Native DOM", false, false);
        Option o53 = new Option(new ObjectId("q5o3"), "Virtual DOM", true ,false);

        options5.add(o51);
        options5.add(o52);
        options5.add(o53);
        Question q5 = new Question( new ObjectId("q5"), "What DOM does React.js ?",
                "What DOM does React.js interact with?",
                "", QuestionType.ONE_CHOICE, options5);

        QUESTIONS.add(q1);
        QUESTIONS.add(q2);
        QUESTIONS.add(q3);
        QUESTIONS.add(q4);
        QUESTIONS.add(q5);

        QUIZ = new Quiz( new ObjectId("QUIZ"), "React.js quiz",
                "Test your knowledge of React.js", "",
                QuizMode.ONE_WAY_DIRECTION, QUESTIONS);

        MOCK_QUIZ_LIST.add(QUIZ);
    }
}
