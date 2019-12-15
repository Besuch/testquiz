import {
    SET_EMAIL,
    GET_CHOSEN_QUIZ_SUCCESS,
    GET_QUIZ_LIST_SUCCESS,
    GET_QUIZ_NAMES_LIST_SUCCESS,
    RESET_CARD_PAGE_INFO,
    SHOW_NEXT_QUESTION,
    SHOW_PREV_BUTTON,
    SET_CUR_QUIZ_TO_NONE,
    SEND_REPORT_TO_BACKEND_RESULT
} from "../action";

const initialState = {
    email: 'user@gmail.com',

    quizNamesArr:[],

    cardState: 'QuizCard',

    count: 0,

    currentQuiz: null,

    statistics: null
};

export default (state = initialState, action) => {

    const stateManipulations = (state, action) =>{
        console.log("payload ", action.payload);
        const {id, value} = action.payload;
        const questionsArr = state.currentQuiz.questionDtos;
        const currentQuestion = questionsArr.find(question => question.questionId === id);
        const options = currentQuestion.optionDtos.map(option => {
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
        case SET_EMAIL:
            return {... state, email: action.payload}

        case GET_QUIZ_LIST_SUCCESS:
            return {...state, quizArr: action.payload};

        case GET_QUIZ_NAMES_LIST_SUCCESS:
            return {...state, quizNamesArr: action.payload};

        case GET_CHOSEN_QUIZ_SUCCESS:
            return{...state, currentQuiz: action.payload};

        case RESET_CARD_PAGE_INFO:
            return{...state, cardState: action.payload };

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
            return{ ...state, currentQuiz: null, count: 0, statistics: null };

        case SEND_REPORT_TO_BACKEND_RESULT:
            return {...state, statistics: action.payload}

        default:
            return state;
    }
}