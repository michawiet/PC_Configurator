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

export default function SimpleCard({options}) {
  const classes = useStyles();
  
  const handleChange = (e) => {
    options.set(e.target.value, !options.get(e.target.value));
    console.log(options);
  };

  options.set("office", false);
  options.set("gaming", false);
  options.set("photo-editing", false);
  options.set("video-editing", false);
  options.set("3d-rendering", false);

  return (
      <>
      <div className={classes.bigdiv}>
      <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h2">
          Praca biurowa
        </Typography>
      </CardContent>
    </Card>
    <Checkbox
        value = "office"
        onChange={(e)=>handleChange(e)}
        inputProps={{ 'aria-label': 'primary checkbox' }}
      />
    </div>
    <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h2">
          Gry
        </Typography>
      </CardContent>
    </Card>
    <Checkbox
      value = "gaming"
      onChange={(e)=>handleChange(e)}
        inputProps={{ 'aria-label': 'pierwszy' }}
      />
    </div>
    <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h2">Obróbka zdjęć</Typography>
      </CardContent>
    </Card>
    <Checkbox
        value = "photo-editing"
        onChange={(e)=>handleChange(e)}
        inputProps={{ 'aria-label': 'drugi' }}
      />
    </div>
    <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
      
        <Typography variant="h5" component="h2">
          Edycja video
        </Typography>

      </CardContent>
    </Card>
    <Checkbox
        value = "video-editing"
        onChange={(e)=>handleChange(e)}
        inputProps={{ 'aria-label': 'czwarty' }}
      />
    </div>
    <div className={classes.divs}>
    <Card className={classes.root} variant="outlined">
      <CardContent>
      
        <Typography variant="h5" component="h2">
          Renderowanie grafiki 3D
        </Typography>

      </CardContent>
    </Card>
    <Checkbox
        value = "3d-rendering"
        onChange={(e)=>handleChange(e)}
        inputProps={{ 'aria-label': 'czwarty' }}
      />
    </div>
    </div>

    </>
    
  );
}