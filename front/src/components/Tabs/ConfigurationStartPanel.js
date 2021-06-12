import React, { useState } from 'react';
import { makeStyles, Button, Grid, Paper, Typography } from '@material-ui/core';
import Stepper from '../Questions/Stepper';
import StepperImage from '../../icons/stepper_image.jpg';

const useStyles = makeStyles((theme) => ({
  root: {
    alignItems: 'center',
  },
  paper: {
    padding: theme.spacing(2),
  },
  bodyText: {
    maringBottom: theme.spacing(2),
  },
  image: {
    width: '100%',
    height: "750px",
    objectFit: 'cover',
    boxShadow: theme.shadows[5],
  },
}));

function getContent() {
  return [
    {question: "Czym jest konfigurator?", answer: "Konfigurator to ................"},
    {question: "Drugie pytanie?", answer: "no i taki sobie tutaj tekścik"},
    {question: "Trzecie pytanie?", answer: "no i taki sobie tutaj tekścik"},
  ];
}

export default function ConfigurationStartPanel() {
  const classes = useStyles();
  const [startButtonDisabled, setStartButtonDisabled] = useState(false);

  return (
    <>
      {startButtonDisabled ? (<><Stepper/></>) : 
      (
      <>
        <Grid
          container
          direction="row"
          justify="center"
          alignItems="center"
          spacing={4}
        >
          <Grid item xs={6}>
            <img src={StepperImage}
              className={classes.image}
            />
          </Grid>
          <Grid item xs={6}>
            <Paper className={classes.paper} elevation={3}>
              <Grid container direction="column" justify="center" alignItems="center" spacing={1}>
                {getContent().map(({question, answer}, index) => (
                <Grid key={index} item>
                  <Typography variant="h6">{question}</Typography>
                  <Typography variant="body1">{answer}</Typography>
                </Grid>
                ))}
                <Grid item>
                  <Button
                  variant="contained"
                  color="secondary"
                  disabled={startButtonDisabled}
                  onClick={() => setStartButtonDisabled(true)}
                  >
                    Rozpocznij konfigurację
                  </Button>
                </Grid>
              </Grid>
            </Paper>
          </Grid>
        </Grid>
      </>
      )}
    </>
  );
}