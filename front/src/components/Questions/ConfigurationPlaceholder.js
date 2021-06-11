import { Typography, Button, Grid } from '@material-ui/core';
import React from 'react';
import { useHistory } from "react-router-dom";

function ConfigurationPlaceholder({setActiveStep}) {
  let history = useHistory();

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
            Przejdź do konfiguratora
          </Button>
        </Grid>
      </Grid>
    </div>
  )
}

export default ConfigurationPlaceholder
