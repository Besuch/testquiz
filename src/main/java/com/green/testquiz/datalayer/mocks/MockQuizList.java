package com.green.testquiz.datalayer.mocks;

import com.green.testquiz.datalayer.entities.Option;
import com.green.testquiz.datalayer.entities.Question;
import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.entities.Result;
import com.green.testquiz.enums.QuestionType;
import com.green.testquiz.enums.QuizMode;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockQuizList {

    private static Quiz QUIZ;
    private static Set<Question> QUESTIONS = new HashSet<>();
    public static final List<Quiz> MOCK_QUIZ_LIST = new ArrayList<>();
    public static final Set<Result> MOCK_RESULT = new HashSet<>();

    static {
        Set<Option> options1 = new HashSet<>();
        Option o1 = new Option(new ObjectId(), "updateState()", false , false);
        Option o2 = new Option(new ObjectId(), "changeState()", false, false);
        Option o3 = new Option(new ObjectId(), "setState()", true, false);
        Option o4 = new Option(new ObjectId(), "putState()", false, false);
        options1.add(o1);
        options1.add(o2);
        options1.add(o3);
        options1.add(o4);
        Question q1 = new Question(new ObjectId(), "Method to change state",
                "Which method call causes a component to change state?",
                QuestionType.ONE_CHOICE, options1);

        Set<Option> options2 = new HashSet<>();
        Option o21 =  new Option(new ObjectId(), "Yes", true,false);
        Option o22 = new Option(new ObjectId(), "No", false,false);
        Option o23 =  new Option(new ObjectId(), "Maybe", false, false);
        options2.add(o21);
        options2.add(o22);
        options2.add(o23);
        Question q2 = new Question( new ObjectId(),
                "Does Stateless Functional Components in React.js invoke life cycle methods?",
                "Stateless Functional Components",
                QuestionType.ONE_CHOICE, options2);


        Set<Option> options3 = new HashSet<>();
        Option o31 =   new Option(new ObjectId(),
                "Yes, if you pass the ref value for the child component to props and get to the element through a chain of refs",
                true,false);
        Option o32 =  new Option(new ObjectId(),
                "No, it is not possible to access the elements of the child component, since this is contrary to the philosophy of React.js",
                false, false);
        Option o33 = new Option(new ObjectId(), "Yes, you can access using the this keyword", false, false);

        options3.add(o31);
        options3.add(o32);
        options3.add(o33);
        Question q3 = new Question( new ObjectId(),
                "Is it possible to access the value of the element of the child component through the ref of this element?",
                "Child component access",
                QuestionType.ONE_CHOICE, options3);

        Set<Option> options4 = new HashSet<>();
        Option o41 = new Option(new ObjectId(), "Yes", false,false);
        Option o42 = new Option(new ObjectId(), "No", true,false);
        Option o43 = new Option(new ObjectId(), "Sometimes", false,false);
        options4.add(o41);
        options4.add(o42);
        options4.add(o43);
        Question q4 = new Question( new ObjectId(),
                "Is using the setState () method acceptable in componentDidMount()?",
                "Using the setState()",
                QuestionType.ONE_CHOICE, options4);

        Set<Option> options5 = new HashSet<>();
        Option o51 = new Option(new ObjectId(), "Shadow DOM", false, false);
        Option o52 = new Option(new ObjectId(), "Native DOM", false, false);
        Option o53 = new Option(new ObjectId(), "Virtual DOM", true ,false);

        options5.add(o51);
        options5.add(o52);
        options5.add(o53);
        Question q5 = new Question( new ObjectId(),
                "What DOM does React.js interact with?",
                "What DOM does React.js ?",
                QuestionType.ONE_CHOICE, options5);

        Set<Option> options6 = new HashSet<>();
        Option o61 = new Option(new ObjectId(), "render", true, false);
        Option o62 = new Option(new ObjectId(), "componentDidMount", true, false);
        Option o63 = new Option(new ObjectId(), "componentMustUpdate", false,false);
        Option o64 = new Option(new ObjectId(), "componentWillUnmount", true,false);

        options6.add(o61);
        options6.add(o62);
        options6.add(o63);
        options6.add(o64);
        Question q6 = new Question( new ObjectId(),
                "Please choose existing lifecycle methods",
                "The Component lifecycle",
                QuestionType.MULTIPLE_CHOICE, options6);

        QUESTIONS.add(q1);
        QUESTIONS.add(q2);
        QUESTIONS.add(q3);
        QUESTIONS.add(q4);
        QUESTIONS.add(q5);
        QUESTIONS.add(q6);

        QUIZ = new Quiz(new ObjectId(),"React.js quiz",
                "Test your knowledge of React.js", "",
                QuizMode.ONE_WAY_DIRECTION, QUESTIONS);

        MOCK_QUIZ_LIST.add(QUIZ);

        Result r1 = Result.builder()
                .resultId(new ObjectId())
                .statistics(0.3)
                .accountId(MockAccountList.ACCOUNTS.get(0).getAccountId())
                .accountEmail(MockAccountList.ACCOUNTS.get(0).getEmail())
                .cursor(2)
                .quizId(QUIZ.getQuizId())
                .name(QUIZ.getName())
                .shortDescription(QUIZ.getShortDescription())
                .longDescription(QUIZ.getLongDescription())
                .quizMode(QUIZ.getQuizMode())
                .questions(QUIZ.getQuestions())
                .build();

        Result r2 = Result.builder()
                .resultId(new ObjectId())
                .statistics(0.4)
                .accountId(MockAccountList.ACCOUNTS.get(1).getAccountId())
                .accountEmail(MockAccountList.ACCOUNTS.get(1).getEmail())
                .cursor(2)
                .quizId(QUIZ.getQuizId())
                .name(QUIZ.getName())
                .shortDescription(QUIZ.getShortDescription())
                .longDescription(QUIZ.getLongDescription())
                .quizMode(QUIZ.getQuizMode())
                .questions(QUIZ.getQuestions())
                .build();

        MOCK_RESULT.add(r1);
        MOCK_RESULT.add(r2);
    }
}
