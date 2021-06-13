import React, { useState, useEffect } from 'react'
import { useAuth } from "../../AuthContext"
import axios from 'axios';
import { Typography, Table, TableBody, TableCell,TableContainer, TableRow, Paper, Grid, Container, Button } from '@material-ui/core';
import OrderEmptyPlaceholder from './OrderEmptyPlaceholder'
import PaymentDialog from '../payment/PaymentDialog';

function StyledPaper(props) {
  const { children } = props;
  return (
    <Paper elevation={4}>
      {children}
    </Paper>
  );
}

function OrdersHistory() {
  //const classes = useStyles();
  const { currentUser } = useAuth();
  const [orders, setOrders] = useState([]);
  const [open, setOpen] = useState(true);
  const [dialog, setDialog]=useState();
  const handleClickOpen = (totalPrice, orderId) => {
    setOpen(true);
    setDialog(<PaymentDialog
      opend={open}
      totalPrice={totalPrice}
      orderId={orderId}
    />)
    
  };

  const handleCancel = async (orderId) => {
    await axios.post('http://localhost:8080/orders/cancelOrder?email='
      + currentUser.email
      + "&orderId="
      + orderId
    ).catch(() => console.log("cancel order failed"));
    await axios.post('http://localhost:8080/orders/userOrders?email=' + currentUser.email)
      .then(res => {
        setOrders(res.data);
    });
  }

  useEffect(() => {
    //prevent from reading null from user if he is not authenticated
    if(currentUser) {
      axios.post('http://localhost:8080/orders/userOrders?email=' + currentUser.email)
      .then(res => {
        setOrders(res.data);
        console.log(res);
      });
    }
    else {
      setOrders([]);
    }
  },[currentUser]);

  return (
    <div>
      <Container maxWidth="md">
      {orders.length === 0 ? (<OrderEmptyPlaceholder/>) :
        (<>
        <Typography gutterBottom component="h2" variant="h4" align="center">
          Twoje zamówienia
        </Typography>
        <Grid container spacing={4}>
          {orders.map(({date, orderId, products, totalPrice, paymentStatus}, index) => (
            <>
          <Grid item key={index} xs={12}>
            <Grid container 
              direction="row"
              justify="space-between"
              alignItems="center"
              spacing={3}
            >
              <Grid item>
                <Typography color="textSecondary" component="h5" variant="subtitle1" align="left">
                  Zamówienie #{orderId}
                </Typography>
              </Grid>
              <Grid item>
                <Typography color="textSecondary" component="h5" variant="subtitle1" align="left">
                {new Intl.DateTimeFormat('pl-PL', { dateStyle: 'full' }).format(Date.parse(date))}
                </Typography>
              </Grid>
            </Grid>
            <TableContainer component={StyledPaper}>
              <Table>
                <TableBody>
                {products.map(({product,quantity}, index)=>(
                <TableRow key={product.id}>
                  <TableCell>
                    <Grid container direction="row" justify="space-between" alignItems="center">
                      <Grid item>
                        <Grid container spacing={2} alignItems="center">
                          <Grid item>
                            <img src={product.image} alt={product.brand + " " + product.name} style={{
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
                        <Grid container alignItems="center" spacing={2}>
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
                <TableRow>
                  <TableCell>
                    <Grid container direction="row" justify="space-between" alignItems="center" spacing={1}>
                      <Grid item>
                        <strong>Status: </strong>{paymentStatus}
                      </Grid>
                      <Grid item>
                        <strong>Razem: </strong>{new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(totalPrice)}
                      </Grid>
                    </Grid>
                  </TableCell>
                </TableRow>
                {paymentStatus !== "opłacone" ? (
                <TableRow>
                  <TableCell>
                    <Grid container direction="row" justify="space-between" alignItems="center" spacing={1}>
                      <Grid item>
                        <Button variant="contained" color="primary" onClick={() => handleCancel(orderId)}>Anuluj</Button>
                      </Grid>
                      <Grid item>
                        <Button variant="contained" color="primary" onClick={() => handleClickOpen(totalPrice,orderId)}>Zapłać</Button>
                      </Grid>
                    </Grid>
                  </TableCell>
                </TableRow>) : ("") }
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
          </>
          ))}
        </Grid>
        </>)}
      </Container>
      {dialog}
    </div>
  )
}

export default OrdersHistory
