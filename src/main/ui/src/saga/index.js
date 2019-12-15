import {call, put, takeEvery} from 'redux-saga/effects';
import {
    GET_QUIZ_NAMES_LIST,
    getAllQuizesNamesSuccess,
    getChosenQuizSuccess,
    resetCardPageInfo,
    GET_CHOSEN_QUIZ,
    SEND_REPORT_TO_BACKEND,
} from "../action";
import configs from '../configs'

export function* watchQuizSaga() {
    yield takeEvery(GET_QUIZ_NAMES_LIST, getQuizNamesListRequest);
    yield takeEvery(GET_CHOSEN_QUIZ, loadChosenQuiz);
    yield takeEvery(SEND_REPORT_TO_BACKEND, sendReportToBack);
}

function* getQuizNamesListRequest() {
    console.log("configs.getQuizzesUrl = ", configs.getQuizzesUrl)
    const quizzes = yield fetch(configs.getQuizzesUrl).then(response => response.json()).then(response => response)
        .catch(error => error);
    console.log("all quizzes: ", quizzes);
    yield put(getAllQuizesNamesSuccess(quizzes))
}

function* loadChosenQuiz({ payload }) {
    const getQuizByIdUrl = configs.getQuizByIdUrl + payload.quizId + '?email=' + payload.email;
    console.log("getQuizByIdUrl = ", getQuizByIdUrl);
    const quiz = yield fetch(getQuizByIdUrl).then(response => response.json()).then(response => response)
        .catch(error => error);
    yield put(getChosenQuizSuccess(quiz));
    console.log("quiz", quiz);
    yield put(resetCardPageInfo('QuestionCard'));
}

function sendReportToBack({payload}) {
    console.log(JSON.stringify(payload))
}








