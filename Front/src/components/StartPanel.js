import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';
import {  grey, blueGrey } from '@material-ui/core/colors';
import Stepper from './Stepper'

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
    margin: 20,
  },
});

  const ColorButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText(grey[900]),
      backgroundColor: blueGrey[400],
      marginTop: 30,
      marginInlineEnd: 5,
      width: 110,
      left: 1300,

      '&:hover': {
        backgroundColor: blueGrey[100],
      },
    },
  }))(Button);



export default function SimpleCard() {
  const classes = useStyles();
  
const [distable, setDistabl] = React.useState(false);

const [result, ddlvalue]=React.useState()

const handleClick = () => {
  setDistabl(true)
  return  ddlvalue(<Stepper/>);
};

  return (
    <>
      <div>{result}</div>   
      { distable ? <></> : <ColorButton variant="outlined" disabled={distable}  onClick={(handleClick) } >Rozpocznij</ColorButton>}
    </>
    
  );
}