import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Slider from '@material-ui/core/Slider';
import Input from '@material-ui/core/Input';
import { ReactComponent as ZlotyIcon } from '../../icons/poland-zloty-currency-symbol.svg';

const useStyles = makeStyles({
  root: {
    width: 1000,
  },
  input: {
    width: 100,
    fontSize: 20,
  },
});

function valuetext(value) {
    return `${value}`;
}

export default function InputSlider() {
  const classes = useStyles();
  const [value, setValue] = React.useState(3000);
  const handleSliderChange = (event, newValue) => {
    setValue(newValue);
  };
  const handleInputChange = (event) => {
    setValue(event.target.value === '' ? '' : Number(event.target.value));
  };

  const handleBlur = () => {
    if (value < 0) {
      setValue(0);
    } else if (value > 15000) {
      setValue(15000);
    }
  };

  return (
    <div className={classes.root}>
      <Typography component={'span'} id="input-slider" variant="h5" gutterBottom>
        Cena
      </Typography>
      <Grid container spacing={2} alignItems="center">
        <Grid item>
        <ZlotyIcon height={20} width={20}/>
        </Grid>
        <Grid item xs>
          <Slider
            value={typeof value === 'number' ? value : 0}
            onChange={handleSliderChange}
            defaultValue={3000}
            getAriaValueText={valuetext}
            aria-labelledby="discrete-slider-small-steps"
            step={500}
            marks
            min={1000}
            max={9999}
            valueLabelDisplay="auto"
          />
        </Grid>
        <Grid item>
          <Input
            className={classes.input}
            value={value}
            margin="dense"
            onChange={handleInputChange}
            onBlur={handleBlur}
            inputProps={{
              step: 500,
              min: 0,
              max: 9999,
              type: 'number',
              'aria-labelledby': 'input-slider',
            }}
          />
        </Grid>
      </Grid>
    </div>
  );
}