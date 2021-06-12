import React, { useEffect } from 'react';
import { makeStyles, Grid, Slider, Paper, Typography } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(4),
    paddingLeft: 50,
    paddingRight: 50,
    color: theme.palette.text.primary,
    boxShadow: theme.shadows[4],
    width: 900,
  },
  root: {
    paddingTop: theme.spacing(3),
    display: "flex",
    justifyContent: "center",
  },
  title: {
    paddingBottom: 25,
    fontSize: 40,
  }
}));

function valuetext(value) {
    return `${value}`;
}

function marks(MIN_VAL, MAX_VAL, STEP_VAL) {
  const val = (MAX_VAL - MIN_VAL) / STEP_VAL;
  STEP_VAL *= val > 18 ? 4 : (val > 12 ? 3 : 2);
  var arr = [];
  for(var i = MIN_VAL; i <= MAX_VAL; i += STEP_VAL) {
    arr.push({value: i, label: new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(i)})
  }
  return arr;
};

function getMinValue(workload) {
  switch(workload) {
    case "office":
      return 1000;
    case "gaming":
      return 2000;
    case "photo-editing":
      return 2000;
    case "video-editing":
      return 4000;
    case "3d-rendering":
      return 6000;
  }
}

function getMaxValue(workload) {
  switch(workload) {
    case "office":
      return 4000;
    case "gaming":
      return 20000;
    case "photo-editing":
      return 10000;
    case "video-editing":
      return 30000;
    case "3d-rendering":
      return 40000;
  }
}

function getStepValue(workload) {
  switch(workload) {
    case "office":
      return 250;
    case "gaming":
      return 1000;
    case "photo-editing":
      return 500;
    case "video-editing":
      return 1000;
    case "3d-rendering":
      return 1000;
  }
}

export default function SimpleCard({budget, setBudget, workload}) {
  const MIN_VAL = getMinValue(workload);
  const MAX_VAL = getMaxValue(workload);
  const STEP_VAL = getStepValue(workload);

  const classes = useStyles();
  const handleSliderChange = (event, newValue) => {
    setBudget(newValue);
  };

  useEffect(() => {
    if(budget === "-") {
      setBudget((MAX_VAL - MIN_VAL) / 2)
    } else if(budget > MAX_VAL)
      setBudget(MAX_VAL);
    else if(budget < MIN_VAL)
      setBudget(MIN_VAL);
  }, [])

  return (
    <div className={classes.root}>
      <Paper className={classes.paper}>
      <Grid container direction="row" justify="space-between" alignItems="center">
        <Grid item xs={12}>
          <Typography className={classes.title} variant="h1" align="center">
            <strong>{new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(budget)}</strong>
          </Typography>
        </Grid>
        <Grid item xs={12}>
          <Slider
            value={typeof budget === 'number' ? budget : 0}
            onChange={handleSliderChange}
            getAriaValueText={valuetext}
            aria-labelledby="discrete-slider-small-steps"
            step={STEP_VAL}
            marks={marks(MIN_VAL, MAX_VAL, STEP_VAL)}
            min={MIN_VAL}
            max={MAX_VAL}
            valueLabelDisplay="off"
          />
        </Grid>
      </Grid>
      </Paper>
    </div>
  );
}