import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper } from '@material-ui/core';
import { ReactComponent as IntelLogo } from '../../icons/intel-3.svg';
import { ReactComponent as RyzenLogo } from '../../icons/amd-logo-1.svg';

const useStyles = makeStyles((theme) => ({
  root: {
    paddingTop: theme.spacing(3),
    paddingBottom: theme.spacing(6),
  },
  paper: {
    padding: theme.spacing(3),
    textAlign: 'center',
    color: theme.palette.text.primary,
    maxWidth: 460,
    boxShadow: theme.shadows[2],
    '&:hover': {
      cursor: 'pointer',
      boxShadow: theme.shadows[6],
    }
  },
  iconStyle: {
    maxHeight: 100,
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
                  <FormControlLabel value="amd" control={<Radio />}/>
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
                  <FormControlLabel value="intel" control={<Radio />}/>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
        </Grid>
      </RadioGroup>
    </div>
  );
}