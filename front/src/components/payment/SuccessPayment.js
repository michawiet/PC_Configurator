import React from 'react'
import { Alert, AlertTitle } from '@material-ui/lab';

function SuccessPayment() {
  return (
    <div>
        <Alert severity="success">
        <AlertTitle>Powodzenie</AlertTitle>
        Płatnoiść zakończona powodzeniem   
      </Alert>
    </div>
  )
}

export default SuccessPayment
