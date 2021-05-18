import React, { useState, useCallback } from 'react';
import { makeStyles, createMuiTheme } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper } from '@material-ui/core';
import { ReactComponent as NvidiaLogo } from '../../icons/nvidia.svg';
import { ReactComponent as RadeonLogo } from '../../icons/amd-radeon.svg';
//import '../../App.css';

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
    maxWidth: 460,
    '&:hover': {
      cursor: 'pointer',
      boxShadow: theme.shadows[6],
    }
  },
  iconStyle: {
    maxHeight: 100,
    maxWidth: 200,
  },
}));

export default function GpuQuestionPanel() {
  const classes = useStyles();
const [value, setValue] = React.useState('');

const handleChange = (bvalue) => {
    setValue(bvalue);
};

  return (
    <div className={classes.root}>
      <RadioGroup  value={value}>
      <Grid container spacing={10} >
      <Grid item xs = {6} >
          <Paper  elevation={16} className = {classes.paper} onClick={() =>{handleChange('amd');}}  >
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
          <Paper elevation={16} className = {classes.paper} onClick={() =>{handleChange('nvidia');}}>
            <Grid container spacing={1}>
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