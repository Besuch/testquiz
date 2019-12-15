import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import { getAllQuizesNames, setEmail } from '../../action'
import QuizCard from '../../components/quizcard/QuizCard'
import QuestionCard from "../../components/questioncard/QuestionCard";
import ResultCard from "../../components/resultcard/ResultCard";
import Container from '@material-ui/core/Container';


export const QuizList = ({ match }) => {
    const dispatch = useDispatch(); //connect to store
    const store = useSelector(state => state);

    let cardState = store.cardState;
    console.log(match.params.email);

    useEffect(() => { //called the first time when component was loaded
        dispatch(getAllQuizesNames());
//        dispatch(setEmail(match.params.email));
    },[]);

    return (
        <>
            <Container maxWidth="sm">
                {(cardState === 'QuestionCard') && <QuestionCard key={"1"} store={store} />}
                {(cardState === 'ResultCard') && <ResultCard key={"2"} statistics={store.statistics} />}
                {(cardState === 'QuizCard') && <QuizCard key={"3"} store={store} />}
            </Container>
        </>
    )
}