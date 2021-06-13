import { useEffect, useState } from 'react';
import axios from 'axios';
import { makeStyles, Typography, Table, TableBody, TableCell, TableContainer, TableRow, Paper, Grid } from '@material-ui/core';
import PropTypes from 'prop-types';
import { Alert, AlertTitle } from '@material-ui/lab';
import { useHistory } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  appBar: {
    position: 'relative',
  },
  title: {
    marginLeft: theme.spacing(2),
    flex: 1,
  },
  container:{
    marginTop: 50,
  },
  formControl: {
    minWidth: 20,
    justifyContent: 'flex-end',
  },
  checkoutPaper: {
    padding: '15px',
  },
  titleTypography: {
    fontSize: "26px",
  }
}));

function StyledPaper(props) {
  const { children } = props;

  return (
    <Paper elevation={4}>
      {children}
    </Paper>
  );
}

StyledPaper.propTypes = {
  children: PropTypes.element.isRequired,
};

function OrderConfirmation(props) {
  const classes = useStyles();
  const [products, setProducts] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [productCount, setProductCount] = useState(0);
  let history = useHistory();

  useEffect(() => {
    console.log(props);
    if(props.location.orderId === undefined) {
      history.replace("/");
    }
    //check if order id is not passed
      //if  
        //history.replace("/")
      //else
        //fetch data
        //setProducts
        //setTotalPrice
        //setProductCount
  }, [])

  return (
    <div>
      <Alert severity="success">
        <AlertTitle><strong>Płatność zakończona powodzeniem</strong></AlertTitle>
        Pomyślnie dokonano zapłaty za zamównienie <strong>#{props.location.orderId}</strong>
      </Alert>
      <Grid container spacing={4} >
        <Grid item xs={8}>
          <Grid container 
            direction="row"
            justify="space-between"
            alignItems="center"
            style={{paddingBottom: 10}}
            spacing={3}
          >
            <Grid item>
              <Typography className={classes.titleTypography} variant="subtitle2" component="h4" color="textPrimary" display="inline" noWrap>
                Twoje zamówienie&nbsp;
              </Typography>
              <Typography className={classes.titleTypography} variant="subtitle2" component="h4" color="textSecondary" display="inline" noWrap>
                ({productCount})
              </Typography>
            </Grid>
            <Grid item>
            </Grid>
          </Grid>
          <TableContainer component={StyledPaper}>
            <Table>
              <TableBody>
              {products.map(({product, quantity}) => (
                <TableRow key={product.id} >
                  <TableCell>
                    <Grid container direction="row" justify="space-between" alignItems="center">
                      <Grid item>
                        <Grid container spacing={2} alignItems="center">
                          <Grid item>
                            <img src={product.image}
                              alt={product.brand + " " + product.name}
                              style={{
                                height: 72,
                                maxWidth: 72,
                                width: '100%',
                                objectFit: 'contain',
                              }}
                              loading="lazy"
                            />
                          </Grid>
                          <Grid item>
                            {product.brand + " " + product.name}
                          </Grid>
                        </Grid>
                      </Grid>
                      <Grid item>
                        <Grid container alignItems="center" spacing={1}>
                          <Grid item>
                            {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(product.price)}
                          </Grid>
                          <Grid item>
                            Ilość:&nbsp;{quantity}
                          </Grid>
                          <Grid item>
                          </Grid>
                        </Grid>
                      </Grid>
                    </Grid>
                  </TableCell>
                </TableRow>
              ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Grid>
      </Grid>
    </div>
  )
}

export default OrderConfirmation
