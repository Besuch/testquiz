import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {showNextQuestion, resetCardPageInfo, showPrevQuestion,
    sendReportToBackEnd, setCurQuizToNone} from '../../action';
import RadioButtonsGroup from '../radiobuttonsgroup/RadioButtonsGroup';
import CheckboxesGroup from '../checkboxesgroup/CheckboxesGroup';
import {useDispatch} from "react-redux";
import {Report} from '../model/Report';

const useStyles = makeStyles({
    card: {
        minWidth: 275,
    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
        marginTop: 12,
    },


});


export default function QuestionCard(props) {
    const dispatch = useDispatch();

    const {store} = props;
    console.log("store", store)
    const email = store.email;
    let quiz = store.currentQuiz;
    let arrayQuestionsLength = quiz.questionDtos.length;
    let count = store.count;
    let question = quiz.questionDtos[count];
    const classes = useStyles();
    let quizMode = quiz.quizMode;
    const [items, setItems] = React.useState([]);

    const addItem = (optionId) => {
      setItems([
        ...items, optionId
      ]);
    };


    let disableBtn;

    if(quizMode === "MULTI_WAY_DIRECTION") {
        disableBtn = false;
    }else {
        disableBtn = true;
        for (let i = 0; i < question.optionDtos.length; i++) {
            if (question.optionDtos[i].isChecked) disableBtn = false;
        }
    }


    let button;
    if(count === arrayQuestionsLength - 1){
        button = <Button variant="contained" color="default"
            disabled={disableBtn} onClick={ () => {
            dispatch(sendReportToBackEnd(new Report(quiz.quizId, question.questionId, items, true, email)));
            dispatch(resetCardPageInfo("ResultCard"));
            dispatch(setCurQuizToNone());
        }} size="small" >Submit</Button>;
    } else {
        button = <Button variant="contained" color="default"
            disabled={disableBtn} onClick={ () => {
            dispatch(sendReportToBackEnd(new Report(quiz.quizId, question.questionId, items, false, email)));
            dispatch(showNextQuestion({id: question.questionId, items: items}));
        }} size="small" >Next</Button>;
    }

    let returnButton;
    if(count !== 0){
        returnButton =  <Button variant="contained" color="default" onClick={ () =>{
            dispatch(sendReportToBackEnd(new Report(quiz.quizId, question.questionId, items, false, email)));
            dispatch(showPrevQuestion({id: question.questionId, items: items}))} } size="small" >Previous</Button>;
    }

    let optiondisplay = question.questionType === 'ONE_CHOICE' 
    ? <RadioButtonsGroup question = {question} addItem = {addItem}/>
    : <CheckboxesGroup question = {question} addItem = {addItem}/>;


    return (
        <>
            <Card className={classes.card}>
                <CardContent>
                    <Typography variant="h5" component="h2">
                        number: {count + 1}
                    </Typography>
                    <Typography variant="h5" component="h2">
                        Question: {question.text}
                    </Typography>
                </CardContent>
                {optiondisplay}
                <CardActions>
                    {(returnButton && (quizMode !=='ONE_WAY_DIRECTION' ))? returnButton : ""}
                    {button}
                </CardActions>
            </Card>
        </>


    );
}