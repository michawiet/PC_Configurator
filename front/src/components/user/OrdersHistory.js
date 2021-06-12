import React, { useState, useEffect } from 'react'
import { useAuth } from "../../AuthContext"
import axios from 'axios';
import { Typography, Table, TableBody, TableCell,TableContainer, TableRow, Paper, Grid, Container } from '@material-ui/core';
import OrderEmptyPlaceholder from './OrderEmptyPlaceholder'

function StyledPaper(props) {
  const { children } = props;

  return (
    <Paper elevation={4}>
      {children}
    </Paper>
  );
  }
function OrdersHistory() {
  const { currentUser } = useAuth();
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    //prevent from reading null from user if he is not authenticated
    if(currentUser) {
      axios.get('http://localhost:8080/orderlist?email=' + currentUser.email )
      .then(res => {
        setOrders(res.data);
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
          {orders.map(({date, orderId, products, totalPrice}, index) => (
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
                {products.map(({id, image, brand, name, quantity, price}, index)=>(
                <TableRow key={id}>
                  <TableCell>
                    <Grid container direction="row" justify="space-between" alignItems="center">
                      <Grid item>
                        <Grid container spacing={2} alignItems="center">
                          <Grid item>
                            <img src={image} alt={brand + " " + name} style={{
                              height: 72,
                              maxWidth: 72,
                              width: '100%',
                              objectFit: 'contain',
                              }}
                              loading="lazy"
                            />
                          </Grid>
                          <Grid item>
                            {brand + " " + name}
                          </Grid>
                        </Grid>
                      </Grid>
                      <Grid item>
                        <Grid container alignItems="center" spacing={2}>
                          <Grid item>
                            {quantity} szt.
                          </Grid>
                          <Grid item>
                            {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(price)}
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
          ))}
        </Grid>
        </>)}
      </Container>
    </div>
  )
}

export default OrdersHistory
