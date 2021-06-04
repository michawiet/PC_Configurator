import React, { useState } from 'react';
import { makeStyles, Button, Grid, Paper } from '@material-ui/core';
import Stepper from './Stepper';

const useStyles = makeStyles({
  root: {
    alignItems: 'center',
  },
});

export default function SimpleCard() {
  const classes = useStyles();
  const [startButtonDisabled, setStartButtonDisabled] = useState(false);
  const [result, ddlvalue] = useState();
  const handleClick = () => {
    setStartButtonDisabled(true)
    return  ddlvalue(<Stepper/>);
  };

  return (
    <>
      {startButtonDisabled ? (<>{result}</>) : 
      (
      <>
        <Grid
          container
          direction="row"
          justify="center"
          alignItems="center"
          spacing={1}
        >
          <Grid item xs={6}>
            <img src="https://images-ext-1.discordapp.net/external/cxTaGaew-BtvBjVUSBqWYbqqQDH8VpY4IGg0RykUeYM/%3Fresize%3D800%252C531%26ssl%3D1/https/i1.wp.com/itsfoss.com/wp-content/uploads/2020/10/pi-zero-cluster.png?width=720&height=478"
            style={{
              width: '100%',
              objectFit: 'contain',
              }}
              loading="lazy"
            />
          </Grid>
          <Grid item xs={6}>
            <Paper>
              <Button
                variant="contained"
                color="secondary"
                disabled={startButtonDisabled}
                onClick={(handleClick)}
              >
                Rozpocznij
              </Button>
            </Paper>
          </Grid>
        </Grid>
      </>
      )}
    </>
  );
}