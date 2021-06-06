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

function marks(MIN_VAL, MAX_VAL) {
  var arr = [ { value: 1000, label: '1000 zł' },
    { value: 1500, label: '1500 zł' },
    { value: 2000, label: '2000 zł' },
    { value: 3000, label: '3000 zł' },
    { value: 5000, label: '5000 zł' },
    { value: 7000, label: '7000 zł' },
    { value: 10000, label: '10 000 zł' },
    { value: 15000, label: '15 000 zł' },
    { value: 20000, label: '20 000 zł' },
    { value: 25000, label: '25 000 zł' },
    { value: 30000, label: '30 000 zł' },
    { value: 35000, label: '35 000 zł' },
    { value: 40000, label: '40 000 zł' },
  ]
  return arr.filter(x => x.value > MIN_VAL && x.value < MAX_VAL)
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
    if(budget > MAX_VAL)
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
            <strong>{budget + " zł"}</strong>
          </Typography>
        </Grid>
        <Grid item xs={12}>
          <Slider
            value={typeof budget === 'number' ? budget : 0}
            onChange={handleSliderChange}
            getAriaValueText={valuetext}
            aria-labelledby="discrete-slider-small-steps"
            step={STEP_VAL}
            marks={marks(MIN_VAL, MAX_VAL)}
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