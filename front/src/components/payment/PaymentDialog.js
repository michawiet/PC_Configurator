import React from 'react'
import { Alert, AlertTitle } from '@material-ui/lab';
import Dialog from '@material-ui/core/Dialog';
import { useHistory } from "react-router-dom";
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import { withStyles } from '@material-ui/core/styles';
import  {makeStyles} from '@material-ui/core'
import MuiDialogContent from '@material-ui/core/DialogContent';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import PayPalPayOrder from '../payment/PayPalPayOrder';

const useStyles = makeStyles((theme) => ({
  dialog: {
    textAlign: 'center',
    width: 350,
  },

}));

const styles = (theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(2),
  },
  closeButton: {
    position: 'absolute',
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
  },
});

const DialogTitle = withStyles(styles)((props) => {
  const { children, classes, onClose, ...other } = props;
  return (
    <MuiDialogTitle disableTypography className={classes.root} {...other}>
      <Typography variant="h6">{children}</Typography>
      {onClose ? (
        <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
          <CloseIcon />
        </IconButton>
      ) : null}
    </MuiDialogTitle>
  );
});
const DialogContent = withStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
  },
}))(MuiDialogContent);

function PaymentDialog(props) {
  const classes = useStyles();
  let history = useHistory();
  const [open, setOpen] = React.useState(props.opend);
  const handleClose = () => {
    setOpen(false);
  };
  const handleClickOpen = () => {
    setOpen(true);
  };

  return (
    <div>
      <Dialog onClose={handleClose} aria-labelledby="customized-dialog-title" open={open} >
            <DialogTitle id="customized-dialog-title" onClose={handleClose} className={classes.dialog} >
              Zapłać {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(props.totalPrice)}
            </DialogTitle>
            <DialogContent dividers>
            <PayPalPayOrder
                totalPrice={props.totalPrice}
                orderId={props.orderId}
              />
            </DialogContent>
          </Dialog>
    </div>
  )
}

export default PaymentDialog
