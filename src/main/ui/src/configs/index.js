// const URL_GET_QUIZZES_RELATIVE = '/api/quizzes';
const URL_GET_QUIZZES_DEBUG = 'http://localhost:8080/api/quizzes';

export default {
    getQuizzesUrl: URL_GET_QUIZZES_DEBUG,
};


export const getQuizByIdUrl = (quizId, email) => `http://localhost:8080/api/quiz/${quizId}?email=${email}`;
export const postProgressUrl = (quizId, questionId, email) => `http://localhost:8080/api/progress/quizzes/${quizId}/questions/${questionId}/answers?email=${email}`;
export const postResultUrl = (quizId, questionId, email) => `http://localhost:8080/api/result/${quizId}/questions/${questionId}/answers?email=${email}`;

