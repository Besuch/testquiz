import {put, takeEvery} from 'redux-saga/effects';
import {
    GET_QUIZ_NAMES_LIST,
    getAllQuizesNamesSuccess,
    getChosenQuizSuccess,
    resetCardPageInfo,
    GET_CHOSEN_QUIZ,
    SEND_REPORT_TO_BACKEND,
    sendReportToBackEndResult,
} from "../action";
import { getQuizzesUrl, getQuizByIdUrl, postProgressUrl, postResultUrl } from '../configs';

export function* watchQuizSaga() {
    yield takeEvery(GET_QUIZ_NAMES_LIST, getQuizNamesListRequest);
    yield takeEvery(GET_CHOSEN_QUIZ, loadChosenQuiz);
    yield takeEvery(SEND_REPORT_TO_BACKEND, sendReportToBack);
}

function* getQuizNamesListRequest() {
    console.log("getQuizzesUrl = ", getQuizzesUrl())
    const quizzes = yield fetch(getQuizzesUrl())
        .then(response => response.json())
        .then(response => response)
        .catch(error => console.error(error));
    console.log("all quizzes: ", quizzes);
    if (quizzes) {
        yield put(getAllQuizesNamesSuccess(quizzes))
    }
}

function* loadChosenQuiz({ payload }) {
    const url = getQuizByIdUrl(payload.quizId, payload.email);
    const quiz = yield fetch(url)
        .then(response => response.json())
        .then(response => response)
        .catch(error => console.error(error));
    if (quiz) {
        yield put(getChosenQuizSuccess(quiz));
        yield put(resetCardPageInfo('QuestionCard'));
    }
}

function* sendReportToBack({ payload }) {
    const url = payload.isQuizSubmited ? postResultUrl(payload.quizId, payload.questionID, payload.email) : postProgressUrl(payload.quizId, payload.questionID, payload.email);
    const response = yield fetch(url, {
        method: 'post',
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(payload.option)
    }).then(response => response.json())
        .then(response => response)
        .catch(error => console.error(error));
    console.log("sendReportToBack response", response);
    if (response) {
        if (payload.isQuizSubmited) {
            yield put(sendReportToBackEndResult(response));
        }
    }
}