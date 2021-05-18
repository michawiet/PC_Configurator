import React, { useState, useCallback } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper, Button } from '@material-ui/core';
import { ReactComponent as NvidiaLogo } from '../../icons/nvidia.svg';
import { ReactComponent as RadeonLogo } from '../../icons/amd-radeon.svg';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    paddingTop: theme.spacing(3),
    paddingBottom: theme.spacing(6),
    paddingLeft: theme.spacing(10),
    paddingRight: theme.spacing(10),
  },
  paper: {
    padding: theme.spacing(3),
    textAlign: 'center',
    color: theme.palette.text.primary,
  },
  iconStyle: {
    maxHeight: 100,
    maxWidth: 200,
  }
}));

export default function GpuQuestionPanel() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <RadioGroup>
      <Grid container spacing={10}>
      <Grid item xs = {6}>
          <Paper elevation={16} className = {classes.paper}>
            <Grid container spacing={1}>
              <Grid item xs={12}>
                  <RadeonLogo className={classes.iconStyle}/>
              </Grid>
              <Grid item xs={12}>
                <FormControlLabel value="amd" control={<Radio />}/>
              </Grid>
            </Grid>
          </Paper>
        </Grid>
        <Grid item xs = {6}>
          <Paper elevation={16} className = {classes.paper}>
          <Grid container spacing={1}>
              <Grid item xs={12}>
                <NvidiaLogo className={classes.iconStyle}/>
              </Grid>
              <Grid item xs={12}>
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