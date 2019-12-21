import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Radio from '@material-ui/core/Radio';


const useStyles = makeStyles(theme => ({
    formControl: {
        margin: theme.spacing(3),
    },
}));

export default function RadioButtonsGroup(props) {
    const classes = useStyles();
    const {question: {optionDtos}, addItem} = props;

    const handleChange = event => {
        console.log('event.target.value =', event.target.value);
        const optionId = event.target.value;
        addItem(optionId);
        for (let i = 0; i < optionDtos.length ; i++) {
            if(optionDtos[i].optionId === optionId){
                optionDtos[i].isChecked = true;
            }
        }
    };


    return (
        <div>
            <FormControl component="fieldset" className={classes.formControl}>
                <FormLabel component="legend">Make your choice !</FormLabel>
                <RadioGroup aria-label="" name="optionDtos" onChange={handleChange}>
                    {optionDtos.length && optionDtos.map(item => (
                        <FormControlLabel
                        key= {item.optionId}
                        value={item.optionId}
                        control={<Radio color="default" />}
                        label={item.text}
                        labelPlacement="end"
                    />
                    ))}
                </RadioGroup>
                <FormHelperText>Please choose one !</FormHelperText>
            </FormControl>
        </div>
    );
}