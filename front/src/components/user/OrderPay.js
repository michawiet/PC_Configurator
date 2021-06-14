import React, {useState, useEffect} from 'react'
import { makeStyles, Typography, Table, TableBody, TableCell,TableContainer, TableRow, Grid, Paper } from '@material-ui/core';
import { useHistory } from "react-router-dom";
import PayPalPayOrder from '../payment/PayPalPayOrder';
import axios from 'axios';
import { useAuth } from "../../AuthContext";

const useStyles = makeStyles((theme) => ({
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

function OrderPay(props) {
  const classes = useStyles();
  let history = useHistory();
  const { currentUser } = useAuth();
  const [products, setProducts] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [productCount, setProductCount] = useState(0);

  useEffect(() => {
    //prevent from reading null from user if he is not authenticated
    if(props.location.orderId === undefined) {
      history.replace("/");
    }
    if(currentUser) {
      currentUser.getIdToken(/* forceRefresh */ true).then(function(idToken) {
        axios.post("http://localhost:8080/orders/userOrder?" 
            + "token="
            + idToken
            + "&orderId=" 
            + props.location.orderId
        ).then(res => {
          setProducts(res.data.products);
          setTotalPrice(res.data.totalPrice);
          setProductCount(res.data.productCount);
        });
      }).catch(function(error) {
        // Handle error
      });
    }
    else {
      history.replace("/");
    }
  },[currentUser]);

  return (
    <div>
      <Grid container spacing={4}>
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
                                {quantity} szt.
                              </Grid>
                              <Grid item>
                                {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(product.price)}
                              </Grid>
                            </Grid>
                          </Grid>
                        </Grid>
                      </TableCell>
                    </TableRow>
                  ))}
                    <TableCell>
                        <Grid container direction="row" justify="flex-end" alignItems="center" spacing={1}>
                          <Grid item>
                            <strong>Razem:</strong>
                          </Grid>
                          <Grid item>
                            {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(totalPrice)}
                          </Grid>
                        </Grid>
                      </TableCell>
                  </TableBody>
                </Table>
              </TableContainer>
        </Grid>
        <Grid item xs={4}>
          <Paper className={classes.checkoutPaper} elevation={2}>
            <PayPalPayOrder
              totalPrice={props.location.totalPrice}
              orderId={props.location.orderId}
            />
          </Paper>
        </Grid>
      </Grid>
    </div>
  )
}

export default OrderPay
