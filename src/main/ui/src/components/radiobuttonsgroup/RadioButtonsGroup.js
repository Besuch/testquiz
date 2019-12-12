import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';


const useStyles = makeStyles(theme => ({
    formControl: {
        margin: theme.spacing(3),
    },
}));

export default function RadioButtonsGroup(props) {
    const classes = useStyles();
    const {question: {options}, setValue} = props;

    const getValue = () => {
        for (let i = 0; i < options.length; i++) {
            if(options[i].isChecked) {
                console.log(options[i].optionId);
                return options[i].optionId;
            }
        }
    };

    const handleChange = event => {
        const optionId = event.target.value;
        setValue(optionId);
        for (let i = 0; i < options.length ; i++) {
            if(options[i].optionId === optionId){
                options[i].isChecked = true;
            }
        }
    };


    return (
        <div>
            <FormControl component="fieldset" className={classes.formControl}>
                <FormLabel component="legend">Make your choice !</FormLabel>
                {/*<RadioGroup aria-label="" name="options" value={getValue()} onChange={handleChange}>*/}
                    {/*{options.length && options.map(item => (*/}
                        {/*<FormControlLabel*/}
                        {/*key= {item.optionId}*/}
                        {/*value={item.optionId}*/}
                        {/*control={<Radio color="default" />}*/}
                        {/*label={item.text}*/}
                        {/*labelPlacement="end"*/}
                    {/*/>*/}
                    {/*))}*/}
                {/*</RadioGroup>*/}
                <form>
                {options.length && options.map(item => (
                <p><input onClick={handleChange} checked={item.isChecked} key= {item.optionId} name="123" type="radio" value={item.optionId}/> {item.text}</p>
                ))}
                </form>
                <FormHelperText>Please choose one !</FormHelperText>
            </FormControl>
        </div>
    );
}