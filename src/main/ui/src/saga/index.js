import {call, put, takeEvery, throttle} from 'redux-saga/effects';
import {
    GET_QUIZ_NAMES_LIST,
    getAllQuizesNamesSuccess,
    getChosenQuizSuccess,
    resetCardPageInfo,
    GET_CHOSEN_QUIZ,
    SEND_REPORT_TO_BACKEND, sendReportToBackEnd
} from "../action";
import configs from '../configs'

// select - get state

export function* watchQuizSaga() {
    yield takeEvery(GET_QUIZ_NAMES_LIST, getQuizNamesListRequest);
    yield takeEvery(GET_CHOSEN_QUIZ, loadChosenQuiz);
    yield takeEvery(SEND_REPORT_TO_BACKEND, sendReportToBack);
}


function getRequestByUrl(url) {
    // return fetch(url).then(response => response.json()).then(response => response)
    //     .catch(error => error);

    return {
        "quizId": "5df015a59396fc79850be5e5",
        "name": "React.js quiz",
        "shortDescription": null,
        "longDescription": null,
        "quizMode": "TWO_WAY_DIRECTION",
        "questions": [
            {
                "questionId": "5df015a59396fc79850be5cf",
                "text": "Which method call causes a component to change state?",
                "description": null,
                "questionType": "MULTIPLE_CHOICE",
                "options": [
                    {
                        "optionId": "5df015a59396fc79850be5d0",
                        "text": "updateState()",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5d1",
                        "text": "changeState()",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5d2",
                        "text": "setStateless()",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5d3",
                        "text": "setState()",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5d4",
                        "text": "putState()",
                        "isChecked": false
                    }
                ]
            },
            {
                "questionId": "5df015a59396fc79850be5d5",
                "text": "Does Stateless Functional Components in React.js invoke life cycle methods?",
                "description": null,
                "questionType": "ONE_CHOISE",
                "checkedValue": "",
                "options": [
                    {
                        "optionId": "5df015a59396fc79850be5d6",
                        "text": "Yes",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5d7",
                        "text": "No",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5d8",
                        "text": "Maybe",
                        "isChecked": false
                    }
                ]
            },
            {
                "questionId": "5df015a59396fc79850be5d9",
                "text": "Is it possible to access the value of the element of the child component through the ref of this element?",
                "description": null,
                "questionType": "ONE_CHOISE",
                "checkedValue": "",
                "options": [
                    {
                        "optionId": "5df015a59396fc79850be5da",
                        "text": "Yes, if you pass the ref value for the child component to props and get to the element through a chain of refs",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5db",
                        "text": "No, it is not possible to access the elements of the child component, since this is contrary to the philosophy of React.js",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5dc",
                        "text": "Yes, you can access using the this keyword",
                        "isChecked": false
                    }
                ]
            },
            {
                "questionId": "5df015a59396fc79850be5dd",
                "text": "Is using the setState () method acceptable in componentDidMount ()?",
                "description": null,
                "questionType": "ONE_CHOISE",
                "checkedValue": "",
                "options": [
                    {
                        "optionId": "5df015a59396fc79850be5de",
                        "text": "Yes",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5df",
                        "text": "No",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5e0",
                        "text": "Sometimes",
                        "isChecked": false
                    }
                ]
            },
            {
                "questionId": "5df015a59396fc79850be5e1",
                "text": "What DOM does React.js interact with?",
                "description": null,
                "questionType": "ONE_CHOISE",
                "checkedValue": "",
                "options": [
                    {
                        "optionId": "5df015a59396fc79850be5e2",
                        "text": "Shadow DOM",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5e3",
                        "text": "Native DOM",
                        "isChecked": false
                    },
                    {
                        "optionId": "5df015a59396fc79850be5e4",
                        "text": "Virtual DOM",
                        "isChecked": false
                    }
                ]
            }
        ]
    }
}

function* getQuizNamesListRequest() {
    const response = yield fetch(configs.getQuizzesUrl).then(response => response.json()).then(response => response)
        .catch(error => error);
    console.log("all quizzes: ", response);
    yield put(getAllQuizesNamesSuccess(response))
}

function* loadChosenQuiz({payload}) {

    const responce = yield call(
        getRequestByUrl, ("localhost:8080/...." + payload)
    );
    yield put(getChosenQuizSuccess(responce));
    yield put(resetCardPageInfo('QuestionCard'));
}

function sendReportToBack({payload}) {
    console.log(JSON.stringify(payload))
}




// QuizMode
//     ONE_WAY_DIRECTION,
//     TWO_WAY_DIRECTION,
//     MULTI_WAY_DIRECTION

// QuestionType
// ONE_CHOICE,
// MULTIPLE_CHOICE








