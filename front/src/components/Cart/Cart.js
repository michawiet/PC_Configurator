import React, {useState, useEffect} from 'react';
import { useHistory } from "react-router-dom";
import { makeStyles, IconButton, Typography, Table, TableBody, TableCell,
TableContainer, TableRow, Paper, Grid, Button } from '@material-ui/core';
import DeleteOutlinedIcon from '@material-ui/icons/DeleteOutlined';
import axios from 'axios';
import CartEmptyPlaceholder from './CartEmptyPlaceholder';
import PayPalPayment from '../payment/PayPalPayment';
import PropTypes from 'prop-types';
import { useAuth } from "../../AuthContext"

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

function Cart() {
  const baseUrl = "http://localhost:8080/cart/";
  const classes = useStyles();
  let history = useHistory();
  const [products, setProducts] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [productCount, setProductCount] = useState(0);
  const [checkout, setCheckout] = useState(false);
  const { currentUser } = useAuth();

  useEffect(() => {
    //get items from server
    function fetchProducts() {
      if(currentUser) {
        axios.post(baseUrl + "getItemList?email=" + currentUser.email)
          .then(res => {
            setProducts(res.data.products);
            setTotalPrice(res.data.totalPrice);
            setProductCount(res.data.productCount);
          }).catch(() => {
            console.log("exception in post method");
          });
      }
    }
    fetchProducts();
    window.addEventListener('focus', fetchProducts);
    window.addEventListener('cartUpdate', fetchProducts);
    return () => {
      window.removeEventListener('focus', fetchProducts);
      window.removeEventListener('cartUpdate', fetchProducts);
    }
  }, [])
  
  const deleteProducts = () => {
    axios.post(baseUrl + "clear?email=" + currentUser.email)
    .then(res => {
      if(res.data === true) {
        setProducts([]);
        setTotalPrice(0);
        setProductCount(0);
        dispatchEvent(new Event('cartUpdate'));
      }
    }).catch(() => {
      console.log("exception in post method");
    });
  };

  const removeItemFromBasket = (productIdToDelete) =>{
    axios.post(baseUrl + "deleteItem?email="
    + currentUser.email
    + "&productId="
    + productIdToDelete
    ).then(res => {
      if(res.data) {
        setProducts(res.data.products);
        setProductCount(res.data.productCount);
        setTotalPrice(res.data.totalPrice);
        dispatchEvent(new Event('cartUpdate'));
      }
    }).catch(() => {
      console.log("exception in post method");
    });
  };

  const handelCheckout = () => {
    if(currentUser) {
      setCheckout(true);
    } else {
      history.push("/logowanie");
    }
  };

  return (
    <div>
      {(productCount === 0) ? (<CartEmptyPlaceholder/>)
      : (<Grid container spacing={4} >
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
                Koszyk&nbsp;
              </Typography>
              <Typography className={classes.titleTypography} variant="subtitle2" component="h4" color="textSecondary" display="inline" noWrap>
                ({productCount})
              </Typography>
            </Grid>
            <Grid item>
              <Button variant='text' color="inherit" onClick={deleteProducts} endIcon={<DeleteOutlinedIcon/>}>
                wyczyść koszyk
              </Button>
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
                            <IconButton color="inherit" size="small" onClick={() => removeItemFromBasket(product.id)}>
                              <DeleteOutlinedIcon/>
                            </IconButton>
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
        <Grid item xs={4}>
          <Paper elevation={4} className={classes.checkoutPaper}>
            <Grid container direction="row" justify="space-between" alignItems="center" style={{paddingBottom:"10px"}}>
              <Grid item>
                <Typography>Łączna kwota</Typography>
              </Grid>
              <Grid item>
                <Typography><strong>{new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(totalPrice)}</strong></Typography>
              </Grid>
            </Grid>             
            { checkout ? (<PayPalPayment
                            setTotalPrice={setTotalPrice}
                            setProducts={setProducts}
                            setProductCount={setProductCount}
                          />) 
            : (<Button fullWidth variant="contained" color="primary" onClick={handelCheckout}>
                  Kup
                </Button>)}
            </Paper>
          </Grid>
      </Grid>)}
    </div>
  )
}

export default Cart
