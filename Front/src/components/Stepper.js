import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import clsx from 'clsx';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Paper from '@material-ui/core/Paper';
import StepContent from '@material-ui/core/StepContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

import WorkloadTypeQuestionPanel  from './Questions/WorkloadTypeQuestionsPanel';
import ProcessorQuestionPanel  from './Questions/ProcessorQuestionPanel';
import GpuQuestionPanel  from './Questions/GpuQuestionPanel';
import BudgetQuestionPanel  from './Questions/BudgetQuestionPanel';

import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';
import EuroIcon from '@material-ui/icons/Euro';
import GpsFixedIcon from '@material-ui/icons/GpsFixed';

const useColorlibStepIconStyles = makeStyles({
  root: {
    backgroundColor: '#ccc',
    zIndex: 1,
    color: '#fff',
    display: 'flex',
    borderRadius: '100%',
    justifyContent: 'center',
    alignItems: 'center',
  },
  active: {
    backgroundImage:
      'linear-gradient( 136deg, rgb(242,113,33) 0%, rgb(233,64,87) 50%, rgb(138,35,135) 100%)',
    boxShadow: '0 4px 10px 0 rgba(0,0,0,.25)',
  },
  completed: {
    backgroundImage:
      'linear-gradient( 136deg, rgb(242,113,33) 0%, rgb(233,64,87) 50%, rgb(138,35,135) 100%)',
  },
});

function ColorlibStepIcon(props) {
  const classes = useColorlibStepIconStyles();
  const { active, completed } = props;

  const icons = {
    1: <GpsFixedIcon />,
    2: <FavoriteBorderIcon />,
    3: <FavoriteBorderIcon />,
    4: <EuroIcon />,
  };

  return (
    <div
      className={clsx(classes.root, {
        [classes.active]: active,
        [classes.completed]: completed,
      })}
    >
      {icons[String(props.icon)]}
    </div>
  );
}

ColorlibStepIcon.propTypes = {
  /**
   * Whether this step is active.
   */
  active: PropTypes.bool,
  /**
   * Mark the step as completed. Is passed to child components.
   */
  completed: PropTypes.bool,
  /**
   * The label displayed in the step icon.
   */
  icon: PropTypes.node,
};

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    alignItems: 'center',
  },
  button: {
    marginRight: theme.spacing(1),
  },
  instructions: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1),
  },
}));

function getSteps() {
  return [<h1>Główne przeznaczenie komputera</h1>, <h1>Preferowany producent procesora</h1>, <h1>Preferowany producent karty graficznej</h1>, <h1>Budżet</h1>];
}

export default function CustomizedSteppers() {
  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());
  const [nextButtonDisabled, setNextButtonDisabled] = React.useState(false);
  const steps = getSteps();

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return <WorkloadTypeQuestionPanel setNextButtonDisabled={setNextButtonDisabled}/>;
      case 1:
        return <ProcessorQuestionPanel />;
      case 2:
        return <GpuQuestionPanel />;
      case 3:
        return <BudgetQuestionPanel />;
      default:
        return 'Unknown step';
    }
  } 

  const isStepOptional = (step) => {
    return step === 1;
  };

  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  const handleNext = () => {
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleSkip = () => {
    if (!isStepOptional(activeStep)) {
      // You probably want to guard against something like this,
      // it should never occur unless someone's actively trying to break something.
      throw new Error("You can't skip a step that isn't optional.");
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped((prevSkipped) => {
      const newSkipped = new Set(prevSkipped.values());
      newSkipped.add(activeStep);
      return newSkipped;
    });
  };

  const handleReset = () => {
    setActiveStep(0);
  };
  return (
    <div className={classes.root}>
    <Stepper activeStep={activeStep} orientation="vertical">
      {steps.map((label, index) => (
        <Step key={label}>
          <StepLabel StepIconComponent={ColorlibStepIcon} >{label}</StepLabel>
          <StepContent>
            <Typography>{getStepContent(index)}</Typography>
            <div className={classes.actionsContainer}>
              <div>
                <Button disabled={activeStep === 0} onClick={handleBack} className={classes.button}>
                  Wróć
                </Button>
                <Button disabled={nextButtonDisabled} variant="contained" color="primary" onClick={handleNext} className={classes.button}>
                  {activeStep === steps.length - 1 ? 'Zakończ' : 'Dalej'}
                </Button>
              </div>
            </div>
          </StepContent>
        </Step>
      ))}
    </Stepper>
    {activeStep === steps.length && (
      <Paper square elevation={0} className={classes.resetContainer}>
        <Typography>All steps completed - you&apos;re finished</Typography>
        <Button onClick={handleReset} className={classes.button}>
          Reset
        </Button>
      </Paper>
    )}
  </div>
  );
}