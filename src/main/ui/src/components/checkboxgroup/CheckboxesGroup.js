import React, {useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormLabel from '@material-ui/core/FormLabel';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormHelperText from '@material-ui/core/FormHelperText';
import Checkbox from '@material-ui/core/Checkbox';
import {getAllQuizesNames} from "../../action";

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
    },
    formControl: {
        margin: theme.spacing(3),
    },
}));

export default function CheckboxesGroup(props) {
    const {question} = props;
    const classes = useStyles();
    const optArr = question.options;

    const [state, setState] = React.useState({

    });

    const handleChange = name => event => {
        setState({ ...state, [name]: event.target.checked });
    };

    const { gilad, jason, antoine } = state;
    const error = [gilad, jason, antoine].filter(v => v).length !== 2;
    let count = 0;



    return (
        <div className={classes.root}>
            <FormControl component="fieldset" className={classes.formControl}>
                <FormLabel component="legend">Assign responsibility</FormLabel>
                <FormGroup>
                    {question.options.length && question.options.map(item => (

                    <FormControlLabel
                        control={<Checkbox checked={optArr[count]} onChange={handleChange(item.text)} value={item.text} />}
                        label={item.text}
                    />
                    ))}

                    {/*<FormControlLabel*/}
                        {/*control={<Checkbox checked={gilad} onChange={handleChange('gilad')} value="gilad" />}*/}
                        {/*label="Gilad Gray"*/}
                    {/*/>*/}
                    {/*<FormControlLabel*/}
                        {/*control={<Checkbox checked={jason} onChange={handleChange('jason')} value="jason" />}*/}
                        {/*label="Jason Killian"*/}
                    {/*/>*/}
                    {/*<FormControlLabel*/}
                        {/*control={*/}
                            {/*<Checkbox checked={antoine} onChange={handleChange('antoine')} value="antoine" />*/}
                        {/*}*/}
                        {/*label="Antoine Llorca"*/}
                    {/*/>*/}
                </FormGroup>
                <FormHelperText>Be careful</FormHelperText>
            </FormControl>
        </div>
    );
}