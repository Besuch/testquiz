import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {getChosenQuiz} from '../../action'
import {useDispatch} from 'react-redux';


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

export default function QuizCard(props) {

    const {store} = props;
    const quizNamesArr = store.quizNamesArr;
    const email = store.email;

    const dispatch = useDispatch();
    const classes = useStyles();

    return (

        <>
            {quizNamesArr.length && quizNamesArr.map(item => (
                <Card className={classes.card} key={item.quizId}>
                    <CardContent>
                        <Typography className={classes.pos} color="textSecondary">
                            Code of the test is : {item.quizId}
                        </Typography>
                        <Typography className={classes.title} color="textSecondary" gutterBottom>
                            Name of the test: {item.name}
                        </Typography>
                        <Typography variant="body2" component="p">
                            Description: {item.shortDescription}
                        </Typography>
                    </CardContent>
                    <CardActions>
                            <Button onClick={() => dispatch(getChosenQuiz({quizId: item.quizId, email}))} size="small">Learn More</Button>
                    </CardActions>
                </Card>
            ))}
        </>

    );
}

