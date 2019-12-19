const URL_GET_QUIZZES_DEBUG = '/api/quizzes';

export default {
    getQuizzesUrl: URL_GET_QUIZZES_DEBUG,
};


export const getQuizByIdUrl = (quizId, email) => `/api/quiz/${quizId}?email=${email}`;
export const postProgressUrl = (quizId, questionId, email) => `/api/progress/quizzes/${quizId}/questions/${questionId}/answers?email=${email}`;
export const postResultUrl = (quizId, questionId, email) => `/api/result/${quizId}/questions/${questionId}/answers?email=${email}`;

