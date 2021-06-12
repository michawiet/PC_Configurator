import React, {useState, useEffect} from 'react'
import { useAuth } from "../../AuthContext"
import axios from 'axios';
import { makeStyles, IconButton, Typography, Table, TableBody, TableCell,TableContainer, TableRow, Paper, Grid, Button, Container } from '@material-ui/core';
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
    console.log("test1");
    if(currentUser) {
      axios.get('http://localhost:8080/orderlist?email=' + currentUser.email )
    .then(res => {
      console.log("test2");
      console.log(res.data);
      setOrders(res.data);
    });
  }
    },[]);

  return (
    <div>
      <Container maxWidth="md">
      {orders.length === 0 ? (<OrderEmptyPlaceholder/>) :
        (<>
        <Typography gutterBottom component="h2" variant="h4" align="center">
          Twoje zamówienia 
        </Typography>
        <Grid container spacing={2}>
        {orders.map(({date, orderId,products}, index) => (
          <Grid item key={index} xs={12}>
            <Typography gutterBottom component="h5" variant="h6" align="left">
              Zamówienie #{orderId}
            </Typography>
            <TableContainer component={StyledPaper}>
            <Table>
              <TableBody>
            {products.map(({id, productId, image, brand, name, quantity, price}, indexx)=>(
              <TableRow key={id} >
              <TableCell>
                <Grid container direction="row" justify="space-between" alignItems="center">
                  <Grid item>
                    <Grid container spacing={2} alignItems="center">
                      <Grid item>
                        <img src={image} style={{
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
                    <Grid container alignItems="center" spacing={1}>
                      <Grid item>
                        {new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(price)}
                      </Grid>
                      <Grid item>
                        Ilość:&nbsp;{quantity}
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
        ))}
        </Grid>
        </>)}
      </Container>
    </div>
  )
}

export default OrdersHistory
