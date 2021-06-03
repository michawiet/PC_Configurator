import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { makeStyles, withStyles, Stepper, Step, StepLabel, Button, Typography, StepConnector } from '@material-ui/core';
import clsx from 'clsx';

import WorkloadTypeQuestionPanel  from './Questions/WorkloadTypeQuestionsPanel';
import ProcessorQuestionPanel  from './Questions/ProcessorQuestionPanel';
import GpuQuestionPanel  from './Questions/GpuQuestionPanel';
import BudgetQuestionPanel  from './Questions/BudgetQuestionPanel';

import { ReactComponent as TargetIcon } from '../icons/stepper/target_white.svg';
import { ReactComponent as GpuIcon } from '../icons/stepper/gpu_white.svg';
import { ReactComponent as CpuIcon } from '../icons/stepper/cpu_white.svg';
import { ReactComponent as BudgetIcon } from '../icons/stepper/money-bag_white.svg';

const ColorlibConnector = withStyles({
  alternativeLabel: {
    top: 22,
  },
  active: {
    '& $line': {
      backgroundImage:
        'linear-gradient(90deg, rgba(63,81,181,1) 0%, rgba(245,0,87,1) 100%)',
    },
  },
  completed: {
    '& $line': {
      backgroundImage:
        'linear-gradient(90deg, rgba(63,81,181,1) 0%, rgba(63,81,181,1) 100%)',
    },
  },
  line: {
    height: 3,
    border: 0,
    backgroundColor: '#eaeaf0',
    borderRadius: 1,
  },
})(StepConnector);

const useColorlibStepIconStyles = makeStyles({
  root: {
    backgroundColor: '#9e9e9e',
    color: '#fff',
    zIndex: 1,
    width: 50,
    height: 50,
    display: 'flex',
    borderRadius: '50%',
    justifyContent: 'center',
    alignItems: 'center',
  },
  active: {
    backgroundColor: '#f50057',
    boxShadow: '0 4px 10px 0 rgba(0,0,0,.25)',
  },
  completed: {
    backgroundColor: '#3f51b5',
  },
});

function ColorlibStepIcon(props) {
  const classes = useColorlibStepIconStyles();
  const { active, completed } = props;

  const icons = {
    1: <TargetIcon width={35} height={35} />,
    2: <CpuIcon width={35} height={35} />,
    3: <GpuIcon width={35} height={35} />,
    4: <BudgetIcon width={35} height={35}/>,
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
  steper: {
    backgroundColor: '#fff',
    boxShadow: theme.shadows[2],
  }

}));

function getSteps() {
  return ['Główne przeznaczenie komputera', 'Preferowany producent procesora', 'Preferowany producent karty graficznej', 'Budżet'];
}

export default function CustomizedSteppers() {
  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());
  const [nextButtonDisabled, setNextButtonDisabled] = useState(true);
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

  const handleReset = () => {
    setActiveStep(0);
  };
  return (
    <div className={classes.root}>
      <Stepper alternativeLabel activeStep={activeStep} connector={<ColorlibConnector/>} className={classes.steper}>
        {steps.map((label) => (
          <Step key={label}>
            <StepLabel StepIconComponent={ColorlibStepIcon}>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>
    <div>
    {activeStep === steps.length ? (
      <div>
        <Typography component={'span'} className={classes.instructions}>
          All steps completed - you&apos;re finished
        </Typography>
        <Button onClick={handleReset} className={classes.button}>Reset</Button>
      </div>
      ) : (
      <div>
        <Typography component={'span'} className={classes.instructions}>{getStepContent(activeStep)}</Typography>
        <div>
          <Button disabled={activeStep === 0} onClick={handleBack} className={classes.button}>Wstecz</Button>
          <Button
            variant="contained"
            color="primary"
            onClick={handleNext}
            className={classes.button}
            disabled={nextButtonDisabled}
          >
            {activeStep === steps.length - 1 ? 'Zakończ' : 'Dalej'}
          </Button>
        </div>
      </div>
      )}
    </div>
  </div>
  );
}
