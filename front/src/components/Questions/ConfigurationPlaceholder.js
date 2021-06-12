import { Typography, Button, Grid } from '@material-ui/core';
import React from 'react';

function ConfigurationPlaceholder({setActiveStep}) {

  return (
    <div style={{marginTop: "50px"}}>
      <Typography paragraph variant="h4" align="center">Nie mogliśmy stworzyć żadnej konfiguracji 😥</Typography>
      <Typography paragraph variant="h5" color="textSecondary" align="center">Dostosuj swoje parametry</Typography>
      <Grid container justify="center" style={{paddingTop: "20px"}}>
        <Grid item>
          <Button 
            variant="contained"
            color="primary"
            onClick={()=> setActiveStep(0)}
          >
            Powróć do pierwszego kroku
          </Button>
        </Grid>
      </Grid>
    </div>
  )
}

export default ConfigurationPlaceholder
