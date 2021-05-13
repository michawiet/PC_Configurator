import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import { green, grey, blueGrey } from '@material-ui/core/colors';
import Radio from '@material-ui/core/Radio';
import Checkbox from '@material-ui/core/Checkbox';
import Slider from './Slider'
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
  divs:
  {
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

const GreenRadio = withStyles({
    root: {
      color: green[400],
      '&$checked': {
        color: green[600],
      },
    },
    checked: {},
  })((props) => <Radio color="default" {...props} />);

  const ColorButton = withStyles((theme) => ({
    root: {
      color: theme.palette.getContrastText(grey[900]),
      backgroundColor: blueGrey[400],
      marginTop: 5,
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
  const bull = <span className={classes.bullet}>•</span>;
  const [checked, setChecked] = React.useState([]);
  const handleChange = (e) => {
    let data=checked;
    data.push(e.target.value)
    setChecked(data);
    console.warn(checked)
  };

  return (
    <>
      <div className={classes.bigdiv}>
        <Slider/>
      </div>
    </>
    
  );
}