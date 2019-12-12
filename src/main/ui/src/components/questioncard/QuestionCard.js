import React, {useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {showNextQuestion, resetCardPageInfo, showPrevQuestion} from '../../action';
import RadioButtonsGroup from '../radiobuttonsgroup/RadioButtonsGroup';
import {useDispatch} from "react-redux";
//import CheckboxesGroup from "../checkboxgroup/CheckboxesGroup"

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
    let quiz = store.currentQuiz;
    let arrayQuestionsLength = quiz.questions.length;
    let count = store.count;
    let question = quiz.questions[count];
    const classes = useStyles();
    let quizMode = quiz.quizMode;
    const [value, setValue] = React.useState(null);


    let button;
    if(count === arrayQuestionsLength -1){
        button = <Button onClick={ () => {dispatch(resetCardPageInfo("ResultCard"));}} size="small" >Submit</Button>;
    } else {
        button = <Button onClick={ () => { dispatch(showNextQuestion({id: question.questionId, value: value}));} } size="small" >Next</Button>;
    }

    let returnButton;
    if(count !== 0){
        returnButton =  <Button onClick={ () =>{dispatch(showPrevQuestion())} } size="small" >Previous</Button>;
    }




    let optiondisplay  = <RadioButtonsGroup question = {question} setValue = {setValue}/>;

    // if(question.questionType === "MULTIPLE_CHOICE"){
    //     optiondisplay = <CheckboxesGroup question = {question} />
    // } else {
    //     optiondisplay = <RadioButtonsGroup setValue={setValue} value={value} question = {question}/>;
    //
    // }

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
                    {/*<Typography className={classes.pos} color="textSecondary">*/}
                        {/*{JSON.stringify(question.options)}*/}
                    {/*</Typography>*/}
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