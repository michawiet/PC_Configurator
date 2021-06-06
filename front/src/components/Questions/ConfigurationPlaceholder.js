import { Typography, Button, Grid } from '@material-ui/core';
import React from 'react';
import { useHistory } from "react-router-dom";

function ConfigurationPlaceholder() {
  let history = useHistory();

  return (
    <div style={{marginTop: "50px"}}>
      <Typography paragraph variant="h4" align="center">Brak konfiguracji 😥</Typography>
      <Typography paragraph variant="h5" color="textSecondary" align="center">Podaj inne możliwości</Typography>
      <Grid container justify="center" style={{paddingTop: "20px"}}>
        <Grid item>
          <Button 
            variant="contained"
            color="primary"
            onClick={()=>{history.push("/konfigurator")}}
          >
            Przejdź do konfiguratora
          </Button>
        </Grid>
      </Grid>
    </div>
  )
}

export default ConfigurationPlaceholder
