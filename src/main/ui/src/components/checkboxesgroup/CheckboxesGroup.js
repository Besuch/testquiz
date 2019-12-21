import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';


const useStyles = makeStyles(theme => ({
    formControl: {
        margin: theme.spacing(3),
    },
}));

export default function CheckboxesGroup(props) {
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
                <FormGroup aria-label="" name="optionDtos">
                    {optionDtos.length && optionDtos.map(item => (
                        <FormControlLabel
                        key= {item.optionId}
                        value={item.optionId}
                        control={<Checkbox color="default" />}
                        label={item.text}
                        labelPlacement="end"
                        onChange={handleChange}
                    />
                    ))}
                </FormGroup>
                <FormHelperText>You can choose several</FormHelperText>
            </FormControl>
        </div>
    );
}