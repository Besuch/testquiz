import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import { getAllQuizesNames } from '../../action'
import QuizCard from '../../components/quizcard/QuizCard'
import QuestionCard from "../../components/questioncard/QuestionCard";
import ResultCard from "../../components/resultcard/ResultCard";
import Container from '@material-ui/core/Container';


export const QuizList = () => {
    const dispatch = useDispatch(); //connect to store
    const store = useSelector(state => state);

    const quizNameArr = store.quizNamesArr; //map state to props
    let cardState = store.cardState;


    useEffect(() => { //called the first time when component was loaded
        dispatch(getAllQuizesNames())
    },[]);

    return (
        <>
            <Container maxWidth="sm">
                {(cardState === 'QuestionCard') && <QuestionCard key={"1"} store={store} />}
                {(cardState === 'ResultCard') && <ResultCard key={"2"} />}
                {(cardState === 'QuizCard') && <QuizCard key={"3"} quizNameArr={quizNameArr} />}
            </Container>
        </>
    )
}