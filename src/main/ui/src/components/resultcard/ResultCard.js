import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {useDispatch} from "react-redux";
import {resetCardPageInfo} from '../../action';

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
    },
});

export default function ResultCard(props) {
    const classes = useStyles();
    const dispatch = useDispatch();

    const statistics = props.statistics;

    return (
        <>
            <Card className={classes.card}>
                <CardContent>
                    <br/>
                    <Typography variant="h5" component="h2">
                        CONGRATULATION, YOUR RESULT IS <span>{statistics}</span> %
                    </Typography>

                    <Button onClick={() => dispatch(resetCardPageInfo("QuizCard"))} size="small"> OK </Button>
                    <br/>

                </CardContent>
            </Card>
        </>


    );
}