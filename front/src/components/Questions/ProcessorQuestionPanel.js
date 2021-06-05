import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper, Typography } from '@material-ui/core';
import { ReactComponent as IntelLogo } from '../../icons/intel-3.svg';
import { ReactComponent as RyzenLogo } from '../../icons/amd-logo-1.svg';
import { ReactComponent as AnyCpuLogo } from '../../icons/any_cpu.svg';

const useStyles = makeStyles((theme) => ({
  root: {
    paddingTop: theme.spacing(3),
    paddingBottom: theme.spacing(6),
  },
  paper: {
    padding: theme.spacing(3),
    textAlign: 'center',
    color: theme.palette.text.primary,
    width: 350,
    height: 250,
    boxShadow: theme.shadows[4],
    '&:hover': {
      cursor: 'pointer',
      boxShadow: theme.shadows[16],
    }
  },
  iconStyle: {
    maxHeight: 150,
    maxWidth: "60%",
  },
}));

export default function ProcessorQuestionPanel({cpuPreference, setCpuPreference}) {
  const classes = useStyles();

  const handleChange = (bvalue) => {
    setCpuPreference(bvalue);
  };
  return (
    <div className={classes.root}>
      <RadioGroup value={cpuPreference}>
        <Grid container justify="space-evenly" alignItems="center">
          <Grid item>
            <Paper className = {classes.paper} onClick={() =>{handleChange('amd');}}>
              <Grid container direction="column" justify="center" alignItems="center" spacing={1}>
                <Grid item>
                  <RyzenLogo className={classes.iconStyle}/>
                </Grid>
                <Grid item>
                  <FormControlLabel labelPlacement="top" value="amd" control={<Radio />}/>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
          <Grid item>
            <Paper className = {classes.paper} onClick={() =>{handleChange('any');}}>
              <Grid container direction="column" justify="center" alignItems="center" spacing={1}>
                <Grid item>
                  <AnyCpuLogo className={classes.iconStyle}/>
                </Grid>
                <Grid item>
                  <FormControlLabel labelPlacement="top" value="any" control={<Radio />}/>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
          <Grid item>
            <Paper className = {classes.paper} onClick={() =>{handleChange('intel');}}>
            <Grid container direction="column" justify="center" alignItems="center" spacing={1}>
                <Grid item>
                  <IntelLogo className={classes.iconStyle}/>
                </Grid>
                <Grid item>
                  <FormControlLabel labelPlacement="top" value="intel" control={<Radio />}/>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
        </Grid>
      </RadioGroup>
    </div>
  );
}