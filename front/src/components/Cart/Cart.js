import React, {useState, useEffect} from 'react';
import { useHistory } from "react-router-dom";
import { makeStyles, AppBar, Toolbar, IconButton, Typography, Table, TableBody, TableCell,
TableContainer, TableHead, TableRow, Paper, Container, Grid, Button, OutlinedInput } from '@material-ui/core';
import DeleteOutlinedIcon from '@material-ui/icons/DeleteOutlined';
import CloseIcon from '@material-ui/icons/Close';
import axios from 'axios';
import CartEmptyPlaceholder from './CartEmptyPlaceholder';
import PayPalPayment from '../payment/PayPalPayment';

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
  }
}));

function Basket() {
  const classes = useStyles();
  let history = useHistory();
  const [products, setProducts] = useState([]);
  const [productCount, setProductCount] = useState(0);
  const [priceTotal, setPriceTotal]= useState(0);
  const [checkout, setCheckout] = useState(false);

  useEffect(() => {
    //get basket from local storage
    const basketString = localStorage.getItem("cart");
    
    //if basketString is not null, then parse into {id: x, quantity: y}
    if(basketString) {
      var basketItems = JSON.parse(basketString);
      const baseUrl = "http://localhost:8080/products?id=";
      var urls = [];
      //create promises
      basketItems.map(x => urls.push(axios.get(baseUrl + x.id)));
      //axios getAll items
      axios.all(urls).then(axios.spread((...responses) => {
        var productsArr = [];
        for(var i = 0; i < responses.length; i++) {
          productsArr.push({product: responses[i].data, quantity: basketItems[i].quantity});
        }
        setProducts(productsArr);
      }));
    }
    //else return
  }, []);

  useEffect(() => {
    /* update totalPrice, productCount on the product array change */
    var totalPrice = 0;
    var count = 0;
    for(var p of products) {
      count += p.quantity;
      totalPrice += p.quantity * p.product.price;
    }
    setProductCount(count);
    setPriceTotal(totalPrice);
  }, [products])
  
  const handleClose = () => {
    history.push("/loged")
  };

  const deleteProducts = () => {
    setProducts([]);
    localStorage.setItem("cart", []);
  };

  const removeItemFromBasket = (productIdToDelete) =>{
    {/* remove the product by filtering the product array */}
    setProducts(products.filter(item => item.product.id !== productIdToDelete));
    {/* remove the product from the localstorage */}
    const productsLocalStorageString = localStorage.getItem("cart");
    if(productsLocalStorageString) {
      const parsedProducts = JSON.parse(productsLocalStorageString).filter(item => item.id !== productIdToDelete);
      localStorage.setItem("cart", JSON.stringify(parsedProducts));
    }
  };

  return (
    <div>
        <AppBar className={classes.appBar}>
          <Toolbar>
            <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
              <CloseIcon />
            </IconButton>
            <Typography variant="h6" className={classes.title}>
              Koszyk
            </Typography>
          </Toolbar>
        </AppBar>
        <Container fixed className={classes.container}>
          {productCount === 0 ? (<CartEmptyPlaceholder/>) : (<Grid container spacing={4} >
            <Grid item xs={8}>
              <Grid container 
                direction="row"
                justify="space-between"
                alignItems="center"
                style={{paddingBottom: 10}}
                spacing={3}
              >
                <Grid item>
                  <Typography variant={"h4"} component="h4" color="textPrimary" display="inline" noWrap>
                    Koszyk&nbsp;
                  </Typography>
                  <Typography variant={"h4"} component="h4" color="textSecondary" display="inline" noWrap>
                    ({productCount})
                  </Typography>
                </Grid>
                <Grid item>
                  <Button variant='text' color="inherit" onClick={deleteProducts} endIcon={<DeleteOutlinedIcon/>}>
                    wyczyść koszyk
                  </Button>
                </Grid>
              </Grid>
              <TableContainer component={Paper}>
                <Table>
                  <TableBody>
                  {products.map(({product, quantity}) => (
                    <TableRow key={product.id} >
                      <TableCell>
                        <Grid container direction="row" justify="space-between" alignItems="center">
                          <Grid item>
                            <Grid container spacing={2} alignItems="center">
                              <Grid item>
                                <img src={product.image} style={{
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
              <Paper variant="outlined" className={classes.checkoutPaper}>
                <Grid container direction="row" justify="space-between" alignItems="center" style={{paddingBottom:"10px"}}>
                  <Grid item>
                    <Typography>Cena całkowita</Typography>
                  </Grid>
                  <Grid item>
                    <Typography><strong>{new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(priceTotal.toFixed(2))}</strong></Typography>
                  </Grid>
                </Grid>             
                { checkout ? (<PayPalPayment price={priceTotal} />) 
                : (<Button fullWidth variant="contained" color="primary" onClick={() => {setCheckout(true);}}>
                      Kup
                    </Button>)}
                </Paper>
              </Grid>
          </Grid>)}
        </Container>
    </div>
  )
}

export default Basket
