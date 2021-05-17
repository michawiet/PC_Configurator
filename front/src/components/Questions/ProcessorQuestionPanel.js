import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import Radio from '@material-ui/core/Radio';

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

  },
});

export default function SimpleCard() {
  const classes = useStyles();
  const bull = <span className={classes.bullet}>•</span>;
  const [selectedValue, setSelectedValue] = React.useState('a');

  const handleChange = (event) => {
    setSelectedValue(event.target.value);
};
const [selectedValue2, setSelectedValue2] = React.useState('c');

const handleChange2 = (event) => {
    setSelectedValue2(event.target.value);
};

  return (
    <>
      <div className={classes.bigdiv}>
      <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h2">
          Intel
        </Typography>
      </CardContent>
    </Card>
    <Radio
        checked={selectedValue === 'a'}
        onChange={handleChange}
        value="a"
        name="radio-button"
        inputProps={{ 'aria-label': 'A' }}
      />
    </div>
    <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h2">Amd</Typography>
      </CardContent>
    </Card>
    <Radio
        checked={selectedValue === 'b'}
        onChange={handleChange}
        value="b"
        name="radio-button-demo"
        inputProps={{ 'aria-label': 'B' }}
      />
    </div>
    </div>
    </>
  );
}