import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import { getAllQuizesNames, setEmail } from '../../action'
import QuizCard from '../../components/quizcard/QuizCard'
import QuestionCard from "../../components/questioncard/QuestionCard";
import ResultCard from "../../components/resultcard/ResultCard";
import Container from '@material-ui/core/Container';
import auth from "../../common/auth";
import Button from "@material-ui/core/Button";
import './QuizList.css';


export const QuizList = (props) => {
    const dispatch = useDispatch(); //connect to store
    const store = useSelector(state => state);
    let cardState = store.cardState;    
    const requestParams = new URLSearchParams(props.location.search);
    const email = requestParams.get('email');

    useEffect(() => { //called the first time when component was loaded
        dispatch(getAllQuizesNames());
        dispatch(setEmail(email));
    },[email, dispatch]);

    return (
        <>
            <div className="btnDiv">
                <Button  variant="contained" size="large"  onClick={() => {
                    auth.logout();
                    props.history.push('/')
                }} > Logout </Button>
            </div>
            <Container maxWidth="sm">
                {(cardState === 'QuestionCard') && <QuestionCard key={"1"} store={store} />}
                {(cardState === 'ResultCard') && <ResultCard key={"2"} statistics={store.statistics} />}
                {(cardState === 'QuizCard') && <QuizCard key={"3"} store={store} />}

            </Container>

        </>
    )
}