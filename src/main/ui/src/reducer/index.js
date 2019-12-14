import {
    GET_CHOSEN_QUIZ_SUCCESS,
    GET_QUIZ_LIST_SUCCESS,
    GET_QUIZ_NAMES_LIST_SUCCESS,
    SET_QUSTION_ARRAY_LENGTH,
    RESET_CARD_PAGE_INFO,
    SHOW_NEXT_QUESTION,
    SHOW_PREV_BUTTON,
    SET_CUR_QUIZ_TO_NONE
} from "../action";

const initialState = {

    quizNamesArr:[],

    cardState: 'QuizCard',

    count: 0,
    arrayQuestionsLength: null,

    currentQuiz: null,

    answer: 0,

    currentQuestion: null,
};

export default (state = initialState, action) => {

    const stateManipulations = (state, action) =>{
        console.log("payload ", action.payload);
        const {id, value} = action.payload;
        const questionsArr = state.currentQuiz.questions;
        const currentQuestion = questionsArr.find(question => question.questionId === id);
        const options = currentQuestion.options.map(option => {
            if (option.optionId === value) {
                return {
                    ...option,
                    isChecked: true
                }
            }
            return {
                ...option,
                isChecked: false
            }
        } );

        const updatedQuestion = {...currentQuestion,  options: options};

        const newQuesitonArray = questionsArr.map(question => {
            if(question.questionId === id){
                return updatedQuestion;
            }
            return question;
        });
        return newQuesitonArray;
    }


    switch (action.type) {
        case GET_QUIZ_LIST_SUCCESS:
            return {...state, quizArr: action.payload};

        case GET_QUIZ_NAMES_LIST_SUCCESS:
            return {...state, quizNamesArr: action.payload};

        case SET_QUSTION_ARRAY_LENGTH:
            return {...state, arrayQuestionsLength: this.currentQuiz.questions.length };

        case GET_CHOSEN_QUIZ_SUCCESS:
            return{...state, currentQuiz: action.payload};

        case RESET_CARD_PAGE_INFO:
            return{...state, cardState: action.payload};

        case SHOW_NEXT_QUESTION:

            return{...state, count: ++initialState.count, currentQuiz: {
                ...state.currentQuiz,
                    questions: stateManipulations(state, action)
                }};

        case SHOW_PREV_BUTTON:
            return{...state, count: --initialState.count, currentQuiz: {
                    ...state.currentQuiz,
                    questions: stateManipulations(state, action)
                }};

        case SET_CUR_QUIZ_TO_NONE:
            return{ ...state, currentQuiz: "", count: 0,  arrayQuestionsLength: null};

        default:
            return state;
    }
}


// QuizMode
//     ONE_WAY_DIRECTION,
//     TWO_WAY_DIRECTION,
//     MULTI_WAY_DIRECTION

// QuestionType
// ONE_CHOICE,
// MULTIPLE_CHOICE
