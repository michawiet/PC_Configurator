import React, { useState, useCallback } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid, Paper } from '@material-ui/core';
import { ReactComponent as OfficeIcon } from '../../icons/statistics.svg';
import { ReactComponent as GamesIcon } from '../../icons/game-controller.svg';
import { ReactComponent as PhotoIcon } from '../../icons/photo-editing.svg';
import { ReactComponent as VideoIcon } from '../../icons/video-editor.svg';
import { ReactComponent as Graphics3DIcon } from '../../icons/3d-modeling.svg';

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
    boxShadow: theme.shadows[2],
    '&:hover': {
      cursor: 'pointer',
      boxShadow: theme.shadows[8],
    }
  },
  icon: {
    maxWidth: 100,
    maxHeight: 100,
  }
}));

export default function SimpleCard({setNextButtonDisabled}) {
  const classes = useStyles();
  const [value, setValue] = React.useState('');

  const handleChange = (bvalue) => {
    setValue(bvalue);
  };
  return (
    <div className={classes.root}>
      <RadioGroup value={value}>
      <Grid container spacing={5}>
        <Grid item xs = {1} />
        <Grid item xs = {2}>
          <Paper className={classes.paper} onClick={() =>{handleChange('office');}}>
            <OfficeIcon className={classes.icon}/>
            <FormControlLabel value="office" control={<Radio />} labelPlacement="top" label="Praca biurowa"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className={classes.paper} onClick={() =>{handleChange('gaming');}}>
            <GamesIcon className={classes.icon}/>
            <FormControlLabel value="gaming" control={<Radio />} labelPlacement="top" label="Gry"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className={classes.paper} onClick={() =>{handleChange('photo-editing');}}>
            <PhotoIcon className={classes.icon}/>
            <FormControlLabel value="photo-editing" control={<Radio />} labelPlacement="top" label="Obróbka zdjęć"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className={classes.paper} onClick={() =>{handleChange('video-editing');}}>
            <VideoIcon className={classes.icon}/>
            <FormControlLabel value="video-editing" control={<Radio />} labelPlacement="top" label="Edycja filmów"/>
          </Paper>
        </Grid>
        <Grid item xs = {2}>
          <Paper className={classes.paper} onClick={() =>{handleChange('3d-rendering');}}>
            <Graphics3DIcon className={classes.icon}/>
            <FormControlLabel value="3d-rendering" control={<Radio />} labelPlacement="top" label="Modelowanie 3D"/>
          </Paper>
        </Grid>
        <Grid item xs = {1} />
      </Grid>
        </RadioGroup>
    </div>
  );
}