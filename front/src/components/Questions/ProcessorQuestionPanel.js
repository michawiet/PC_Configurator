import React, { useState, useCallback } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper } from '@material-ui/core';
import { ReactComponent as IntelLogo } from '../../icons/intel-3.svg';
import { ReactComponent as RyzenLogo } from '../../icons/amd-logo-1.svg';

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

export default function ProcessorQuestionPanel() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <RadioGroup>
        <Grid container spacing={10}>
        <Grid item xs = {6}>
            <Paper elevation={16} className = {classes.paper}>
              <Grid container spacing={1}>
                <Grid item xs={12}>
                  <RyzenLogo className={classes.iconStyle}/>
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
                  <IntelLogo className={classes.iconStyle}/>
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