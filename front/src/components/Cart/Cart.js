import React, {useState, useEffect} from 'react';
import { useHistory } from "react-router-dom";
import { makeStyles, AppBar, Toolbar, IconButton, Typography, Table, TableBody, TableCell,
TableContainer, TableHead, TableRow, Paper, Container, Grid, Button, OutlinedInput } from '@material-ui/core';
import DeleteOutlinedIcon from '@material-ui/icons/DeleteOutlined';
import DeleteIcon from '@material-ui/icons/Delete';
import CloseIcon from '@material-ui/icons/Close';
import axios from 'axios';
import BasketEmptyPlaceholder from './CartEmptyPlaceholder'
import PayPalPayment from '../payment/PayPalPayment'

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
  const [priceTotal, setPriceTotal]= useState(0);
  const [checkout, setCheckout] = useState(false);

  useEffect(() => {
    //get basket from local storage
    const basketString = localStorage.getItem("basket");
    var totalPrice = 0 ;
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
          totalPrice += responses[i].data.price * basketItems[i].quantity;
          productsArr.push({product: responses[i].data, quantity: basketItems[i].quantity});
        }
        setProducts(productsArr);
        setPriceTotal(totalPrice);
      }));
    }
    //else return
  }, []);
  
  const handleClose = () => {
    history.push("/loged")
  };

  const deleteProducts = () => {
    localStorage.setItem("basket", []);
    setProducts([]);
    setPriceTotal(0);
  };

  const removeItemFromBasket = (props) =>{
    console.log(props);
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
            <Button variant='outlined' color="inherit" onClick={deleteProducts}>wyczyść koszyk</Button>
          </Toolbar>
        </AppBar>
        <Container fixed className={classes.container}>
          <Grid container spacing={4} >
            <Grid item xs={9}>
              <Grid container 
                direction="row"
                justify="space-between"
                alignItems="center"
                style={{paddingBottom: 10}}
                spacing={3}
              >
                <Grid item>
                  <Typography variant={"h4"} component="h4" color="textSecondary" display="block" noWrap>
                    Koszyk&nbsp;({products.length})
                  </Typography>
                </Grid>
                <Grid item>
                  <Button variant='outlined' color="inherit" onClick={deleteProducts} endIcon={<DeleteOutlinedIcon/>}>
                    wyczyść koszyk
                  </Button>
                </Grid>
              </Grid>
              <TableContainer component={Paper}>
                <Table>
                  <TableBody>
                  {products.map(({product, quantity}) => (
                    <TableRow key={product.id} hover>
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
                            <Grid container alignItems="center" justify spacing={1}>
                              <Grid item>
                                {product.price.toFixed(2) + " zł"}
                              </Grid>
                              <Grid item>
                                {"Ilość: " + quantity}
                              </Grid>
                              <Grid item>
                                <IconButton onClick={() => removeItemFromBasket(product.id)}>
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
            <Grid item xs={3}>
              <Paper elevation={2} className={classes.checkoutPaper}>
                <Grid container direction="row" justify="space-between" alignItems="center">
                  <Grid item>
                    <Typography>Cena całkowita</Typography>
                  </Grid>
                  <Grid item>
                    <Typography>{priceTotal + " zł"}</Typography>
                  </Grid>
                </Grid>             
                { checkout ? (<PayPalPayment price={priceTotal} />) 
                : (<Typography>
                    <Button fullWidth variant="outlined" color="primary" onClick={() => {setCheckout(true);}}>
                      Kup
                    </Button>
                  </Typography>)}
                    </Paper>
                  </Grid>
                </Grid>
        </Container>
    </div>
  )
}

export default Basket
