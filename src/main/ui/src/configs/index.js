// const URL_GET_QUIZZES_RELATIVE = '/api/quizzes';
const URL_GET_QUIZZES_DEBUG = 'http://localhost:8080/api/quizzes';
const URL_GET_QUIZ_BY_ID_DEBUG = 'http://localhost:8080/api/quiz/';
const URL_POST_PROGRESS_DEBUG = 'http://localhost:8080/api/progress/quizzes/{quizId}/questions/{questionId}/answers';

export default {
    getQuizzesUrl: URL_GET_QUIZZES_DEBUG,
    getQuizByIdUrl: URL_GET_QUIZ_BY_ID_DEBUG,
    postProgressUrl: URL_POST_PROGRESS_DEBUG,
};