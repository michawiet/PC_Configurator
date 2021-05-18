import React, { useState, useCallback } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper } from '@material-ui/core';
import { ReactComponent as OfficeIcon } from '../../icons/081-cooling-fan.svg';
import { ReactComponent as GamesIcon } from '../../icons/005-sd-card-2.svg';
import { ReactComponent as PhotoIcon } from '../../icons/007-computer.svg';
import { ReactComponent as VideoIcon } from '../../icons/031-battery.svg';
import { ReactComponent as Graphics3DIcon } from '../../icons/057-scanner.svg';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    paddingBottom: theme.spacing(3),
    paddingTop: theme.spacing(3),
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.primary,
  },
}));

export default function SimpleCard({setNextButtonDisabled}) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <RadioGroup>
      <Grid container spacing={3}>
        <Grid item xs = {1} />
        <Grid item xs = {2}>
          <Paper className = {classes.paper}>
            <OfficeIcon/>
            <FormControlLabel value="office" control={<Radio />} label="Praca biurowa"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className = {classes.paper}>
            <GamesIcon/>
            <FormControlLabel value="gaming" control={<Radio />} label="Gry"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className = {classes.paper}>
            <PhotoIcon/>
            <FormControlLabel value="photo-editing" control={<Radio />} label="Obróbka zdjęć"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className = {classes.paper}>
            <VideoIcon/>
            <FormControlLabel value="video-editing" control={<Radio />} label="Edycja filmów"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className = {classes.paper}>
            <Graphics3DIcon/>
            <FormControlLabel value="3d-rendering" control={<Radio />} label="Renderowanie 3D"/>
          </Paper>
        </Grid>
        <Grid item xs = {1} />
      </Grid>
        </RadioGroup>
    </div>
  );
}