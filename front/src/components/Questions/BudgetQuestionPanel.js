import React from 'react';
import { makeStyles, Grid, Slider, OutlinedInput, Paper, withStyles, Typography, Tooltip } from '@material-ui/core';
import { ReactComponent as ZlotyIcon } from '../../icons/poland-zloty-currency-symbol.svg';
import PropTypes from 'prop-types';

const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(3),
    color: theme.palette.text.primary,
    boxShadow: theme.shadows[2],
  },
  root: {
    paddingTop: theme.spacing(3),
  },
}));

function ValueLabelComponent(props) {
  const { children, open, value } = props;

  return (
    <Tooltip open={open} enterTouchDelay={0} placement="top" title={value}>
      {children}
    </Tooltip>
  );
}

ValueLabelComponent.propTypes = {
  children: PropTypes.element.isRequired,
  open: PropTypes.bool.isRequired,
  value: PropTypes.number.isRequired,
};

function valuetext(value) {
    return `${value}`;
}

const marks = [
  {
    value: 1000,
    label: '1000 zł',
  },
  {
    value: 3000,
    label: '3000 zł',
  },
  {
    value: 5000,
    label: '5000 zł',
  },
  {
    value: 7000,
    label: '7000 zł',
  },
  {
    value: 12000,
    label: '12 000 zł',
  },
  {
    value: 20000,
    label: '20 000 zł',
  },
];

export default function SimpleCard({budget, setBudget}) {
  const MIN_VAL = 1000;
  const MAX_VAL = 20000;
  const STEP_VAL = 1000;

  const classes = useStyles();
  const handleSliderChange = (event, newValue) => {
    setBudget(newValue);
  };
  const handleInputChange = (event) => {
    setBudget(event.target.value === '' ? '' : Number(event.target.value));
  };

  const handleBlur = () => {
    if (budget < MIN_VAL) {
      setBudget(MIN_VAL);
    } else if (budget > MAX_VAL) {
      setBudget(MAX_VAL);
    }
  };

  const customSlider = withStyles({
    valueLabel: {
      left: "calc(-50% + 12px)",
      top: -22,
      "& *": {
        background: "transparent",
        color: "#000"
      }
    },
  })(Slider);

  return (
    <div className={classes.root}>
      <Paper className={classes.paper}>
      <Grid container direction="row" justify="space-between" alignItems="center">
        <Grid item xs={12}>
          <Typography align="center">
            {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(budget)}
          </Typography>
        </Grid>
        <Grid item xs={8}>
          <Slider
            ValueLabelComponent={ValueLabelComponent}
            value={typeof budget === 'number' ? budget : 0}
            onChange={handleSliderChange}
            getAriaValueText={valuetext}
            aria-labelledby="discrete-slider-small-steps"
            step={STEP_VAL}
            marks={marks}
            min={MIN_VAL}
            max={MAX_VAL}
            valueLabelDisplay="on"
          />
        </Grid>
      </Grid>
      </Paper>
    </div>
  );
}