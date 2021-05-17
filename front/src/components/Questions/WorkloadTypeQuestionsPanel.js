import React, { useState, useCallback } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { FormControlLabel, RadioGroup, Radio, Grid } from '@material-ui/core';
import { ReactComponent as SomeIcon } from '../../icons/081-cooling-fan.svg';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  }, 
  divs: {
    height: 200,
    width: 200,
    margin: 50,
  },
  bigdiv: {
    display: "flex",
    justifyContent:'center',
    alignItems:'center'
  },
  header:{
    textAlign: 'center',
  } 
});

export default function SimpleCard({setNextButtonDisabled}) {
  //const classes = useStyles();

  const checkboxOnChange = useCallback(event => {
    console.log(event.target.value);
    setNextButtonDisabled(event.target.value);
  }, [setNextButtonDisabled]);

  return (
    <div>
      <div>
        <Grid container spacing={10} direction="row" justify="flex-start" alignItems="center">
          <Grid item><SomeIcon width={30} height={30}/></Grid>
          <Grid item><SomeIcon width={30} height={30}/></Grid>
          <Grid item><SomeIcon width={30} height={30}/></Grid>
        </Grid>
        <RadioGroup row={true} >
          <FormControlLabel labelPlacement="top" value="office" control={<Radio />} label="Praca biurowa"/>
          <FormControlLabel labelPlacement="top" value="gaming" control={<Radio />} label="Gry"/>
          <FormControlLabel labelPlacement="top" value="photo-editing" control={<Radio />} label="Obróbka zdjęć"/>
          <FormControlLabel labelPlacement="top" value="video-editing" control={<Radio />} label="Edycja filmów"/>
          <FormControlLabel labelPlacement="top" value="3d-rendering" control={<Radio />} label="Renderowanie 3D"/>
        </RadioGroup>
      </div>
    </div>
  );
}