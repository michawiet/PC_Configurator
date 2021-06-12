import React, { useState } from 'react';
import { makeStyles, Button, Grid, Paper, Typography } from '@material-ui/core';
import Stepper from '../Questions/Stepper';
import StepperImage from '../../icons/stepper_image.jpg';
import TuneIcon from '@material-ui/icons/Tune';

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
    height: "650px",
    objectFit: 'cover',
    boxShadow: theme.shadows[5],
  },
  titlePaper: {
    padding: theme.spacing(1),
    marginBottom: theme.spacing(2),
    margin: "auto",
    textAlign: "center",
    width: "32%",
  },
  titleText: {
    fontSize: "20px",
  },
}));

function getContent() {
  return [
    {question: "Czym jest konfigurator?", answer: "Konfigurator to miejsce gdzie możesz zrobić rzeczy, które dadzą wyniki - możesz z tymi wynikami zrobić operację, i następnie musisz być zalogowany by tą operację dokończyć."},
    {question: "Drugie pytanie?", answer: "Konfigurator to miejsce gdzie możesz zrobić rzeczy, które dadzą wyniki - możesz z tymi wynikami zrobić operację, i następnie musisz być zalogowany by tą operację dokończyć."},
    {question: "Trzecie pytanie?", answer: "Konfigurator to miejsce gdzie możesz zrobić rzeczy, które dadzą wyniki - możesz z tymi wynikami zrobić operację, i następnie musisz być zalogowany by tą operację dokończyć."},
  ];
}

export default function ConfigurationStartPanel() {
  const classes = useStyles();
  const [startButtonDisabled, setStartButtonDisabled] = useState(false);

  return (
    <>
      {startButtonDisabled ? (<><Stepper/></>) : 
      (<>
        <Grid container direction="row" justify="space-between" alignItems="stretch" spacing={2}>
          <Grid item xs={12}>
            <Paper elevation={3} className={classes.titlePaper}>
              <Typography variant="overline" align="center" className={classes.titleText}>
                <strong>Konfigurator komputera</strong>
              </Typography>
            </Paper>
          </Grid>
          <Grid item xs={6}>
            <img src={StepperImage} alt="Konfigurator komputera" className={classes.image}/>
          </Grid>
          <Grid item xs={6}>
            <Paper className={classes.paper} elevation={3}>
              <Grid container direction="column" justify="center" alignItems="stretch" spacing={1}>
                {getContent().map(({question, answer}, index) => (
                <Grid key={index} item>
                  <Typography variant="h6">{question}</Typography>
                  <Typography variant="body1" align="justify">{answer}</Typography>
                </Grid>
                ))}
                <Grid item xs={12}>
                  <Button
                    startIcon={<TuneIcon/>}
                    size="large"
                    fullWidth
                    variant="contained"
                    color="secondary"
                    disabled={startButtonDisabled}
                    onClick={() => setStartButtonDisabled(true)}
                  >
                    <strong>Rozpocznij konfigurację</strong>
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