import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
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
  header: {
    textAlign: 'center',
  } 
});

export default function SimpleCard() {
  const classes = useStyles();
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