package com.green.testquiz;

import com.green.testquiz.datalayer.entities.Quiz;
import com.green.testquiz.datalayer.mocks.MockQuizList;
import com.green.testquiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.green.testquiz")
public class QuizApplication implements CommandLineRunner {

    @Autowired
    private QuizRepository quizRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

    @Override
    public void run(String... args) {
        initMockQuizToMongoDb();
    }

    private void initMockQuizToMongoDb(){
	    if (quizRepository.findAll().size()==0){
            Quiz mockedQuiz = MockQuizList.MOCK_QUIZ_LIST.get(0);
            quizRepository.save(mockedQuiz);
            System.out.println("Quiz SAVED ! id=" + mockedQuiz.getQuizId().toString());
        }
    }
}
