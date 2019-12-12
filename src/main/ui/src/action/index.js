export const GET_QUIZ_LIST_SUCCESS = 'GET_QUIZ_LIST_SUCCESS';
export const GET_QUIZ_NAMES_LIST = 'GET_QUIZ_NAMES_LIST';
export const GET_QUIZ_NAMES_LIST_SUCCESS = 'GET_QUIZ_NAMES_LIST_SUCCESS';
export const SET_QUSTION_ARRAY_LENGTH = 'SET_QUSTION_ARRAY_LENGTH';
export const GET_CHOSEN_QUIZ = 'GET_CHOSEN_QUIZ';
export const GET_CHOSEN_QUIZ_SUCCESS = 'GET_CHOSEN_QUIZ_SUCCESS';
export const RESET_CARD_PAGE_INFO = 'RESET_CARD_PAGE_INFO';
export const SHOW_NEXT_QUESTION = 'SHOW_NEXT_QUESTION';
export const SHOW_NEXT_QUESTION_SUCCESS = 'SHOW_NEXT_QUESTION_SUCCESS';
export const SHOW_PREV_BUTTON ='SHOW_PREV_BUTTON';

export function getAllQuizesNames() {
    return {
        type: GET_QUIZ_NAMES_LIST,
    }
}

export function getAllQuizesNamesSuccess(payload) {
    return {
        type: GET_QUIZ_NAMES_LIST_SUCCESS, payload
    }
}

export function resetCardPageInfo(payload){
    return{
        type: RESET_CARD_PAGE_INFO, payload
    }
}

export function getChosenQuiz(payload) {
    return{
        type: GET_CHOSEN_QUIZ, payload
    }
}

export function getChosenQuizSuccess(payload) {
    return{
        type: GET_CHOSEN_QUIZ_SUCCESS, payload
    }
}

export function showNextQuestion(paylod) {
    return{
        type: SHOW_NEXT_QUESTION,
        payload: paylod
    }
}

export function showNextQuestionSuccess() {
    return{
        type: SHOW_NEXT_QUESTION_SUCCESS
    }
}

export function showPrevQuestion() {
    return{
        type: SHOW_PREV_BUTTON
    }
}

