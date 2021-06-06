import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { makeStyles, withStyles, Stepper, Step, StepLabel, Button, Typography, StepConnector, Grid, Paper } from '@material-ui/core';
import NavigateNextOutlinedIcon from '@material-ui/icons/NavigateNextOutlined';
import NavigateBeforeOutlinedIcon from '@material-ui/icons/NavigateBeforeOutlined';
import clsx from 'clsx';

import WorkloadTypeQuestionPanel  from './WorkloadTypeQuestionsPanel';
import ProcessorQuestionPanel  from './ProcessorQuestionPanel';
import GpuQuestionPanel  from './GpuQuestionPanel';
import BudgetQuestionPanel  from './BudgetQuestionPanel';

import { ReactComponent as TargetIcon } from '../../icons/stepper/target_white.svg';
import { ReactComponent as GpuIcon } from '../../icons/stepper/gpu_white.svg';
import { ReactComponent as CpuIcon } from '../../icons/stepper/cpu_white.svg';
import { ReactComponent as BudgetIcon } from '../../icons/stepper/money-bag_white.svg';

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
  const iconSize = 35;
  const icons = {
    1: <TargetIcon width={iconSize} height={iconSize} />,
    2: <CpuIcon width={iconSize} height={iconSize} />,
    3: <GpuIcon width={iconSize} height={iconSize} />,
    4: <BudgetIcon width={iconSize} height={iconSize}/>,
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
    alignItems: 'center',
  },
  instructions: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1),
  },
  stepper: {
    backgroundColor: '#fff',
    boxShadow: theme.shadows[1],
  }, 
  stepperButtons: {
    display: "flex", 
    justifyContent: "center",
    paddingTop: 20,
  },
  paper: {
    paddingTop: "8px",
    paddingBottom: "8px",
    paddingLeft: "22px",
    paddingRight: "22px",
  },
  titleTypography: {
    fontSize: "0.9375rem",
  }
}));

function getSteps() {
  return ['Zastosowanie',
  'Procesor',
  'Karta graficzna',
  'Budżet'];
}

function getStepTitle(index) {
  const title = ["Wybierz główne przeznaczenie komputera",
    "Wybierz preferowanego producenta procesora",
    "Wybierz preferowanego producenta karty graficznej",
    "Określ swój budżet",
  ];
  return title[index];
}

export default function CustomizedSteppers() {
  const classes = useStyles();
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const [nextButtonDisabled, setNextButtonDisabled] = useState(true);
  const [workload, setWorkload] = useState("");
  const [cpuPreference, setCpuPreference] = useState("any");
  const [gpuPreference, setGpuPreference] = useState("any");
  const [budget, setBudget] = useState(3000);
  const steps = getSteps();

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return <WorkloadTypeQuestionPanel
          setWorkload = {setWorkload}
          workload = {workload}
          setNextButtonDisabled={setNextButtonDisabled}
        />;
      case 1:
        return <ProcessorQuestionPanel
          cpuPreference={cpuPreference}
          setCpuPreference={setCpuPreference}
        />;
      case 2:
        return <GpuQuestionPanel
          gpuPreference={gpuPreference}
          setGpuPreference={setGpuPreference}
        />;
      case 3:
        return <BudgetQuestionPanel
          budget={budget}
          setBudget={setBudget}
          workload = {workload}
        />;
      default:
        return 'Unknown step';
    }
  } 

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
      <Stepper alternativeLabel activeStep={activeStep} connector={<ColorlibConnector/>} className={classes.stepper}>
        {steps.map((label) => (
          <Step key={label}>
            <StepLabel StepIconComponent={ColorlibStepIcon}>
              <Typography variant="overline">
              {label}
              </Typography>
            </StepLabel>
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
          <Grid container style={{paddingTop: "20px"}} direction="row" justify="space-between"alignItems="center">
            <Grid item>
              <Button size="large" variant="outlined" disabled={activeStep === 0}
                onClick={handleBack} className={classes.button}
                startIcon={<NavigateBeforeOutlinedIcon/>}
              >
                Wstecz
              </Button>
            </Grid>
            <Grid item>
              <Paper className={classes.paper} elevation={1} >
                <Typography variant="overline" align="center" className={classes.titleTypography}>
                  {getStepTitle(activeStep)}
                </Typography>
              </Paper>
            </Grid>
            <Grid item>
              <Button size="large" variant="contained" color="primary"
                onClick={handleNext} disabled={nextButtonDisabled}
                endIcon={<NavigateNextOutlinedIcon/>}
                disableElevation
              >
                Dalej
              </Button>
            </Grid>
          </Grid>
        <Typography component={'span'} className={classes.instructions}>{getStepContent(activeStep)}</Typography>
      </div>
      )}
    </div>
  </div>
  );
}
