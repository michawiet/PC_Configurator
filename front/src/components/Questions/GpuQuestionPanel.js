import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper } from '@material-ui/core';
import { ReactComponent as NvidiaLogo } from '../../icons/nvidia.svg';
import { ReactComponent as RadeonLogo } from '../../icons/amd-radeon.svg';

const useStyles = makeStyles((theme) => ({
  root: {
    paddingTop: theme.spacing(3),
  },
  paper: {
    padding: theme.spacing(3),
    textAlign: 'center',
    color: theme.palette.text.primary,
    maxWidth: 460,
    boxShadow: theme.shadows[4],
    '&:hover': {
      cursor: 'pointer',
      boxShadow: theme.shadows[16],
    }
  },
  iconStyle: {
    maxHeight: 100,
    maxWidth: "60%",
  },
}));

export default function GpuQuestionPanel({gpuPreference, setGpuPreference}) {
  const classes = useStyles();
  const handleChange = (bvalue) => {
    setGpuPreference(bvalue);
  };

  return (
    <div className={classes.root}>
      <RadioGroup  value={gpuPreference}>
        <Grid container justify="space-evenly" alignItems="center">
          <Grid item>
            <Paper className = {classes.paper} onClick={() =>{handleChange('amd');}}  >
              <Grid container direction="column" justify="center" alignItems="center" spacing={1}>
                <Grid item xs={12}>
                  <RadeonLogo className={classes.iconStyle}/>
                </Grid>
                <Grid item xs={12}>
                  <FormControlLabel value="amd" control={<Radio />}/>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
          <Grid item>
            <Paper className = {classes.paper} onClick={() =>{handleChange('nvidia');}}>
              <Grid container direction="column" justify="center" alignItems="center" spacing={1}>
                <Grid item xs={12}>
                  <NvidiaLogo className={classes.iconStyle}/>
                </Grid>
                <Grid item xs={12}>
                  <FormControlLabel value="nvidia" control={<Radio />}/>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
        </Grid>
      </RadioGroup>
    </div>
  );
}