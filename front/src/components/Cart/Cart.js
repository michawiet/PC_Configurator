import React, {useState, useEffect} from 'react';
import { useHistory } from "react-router-dom";
import { makeStyles, Dialog, AppBar, Toolbar, IconButton, Typography, Slide, Table, TableBody, TableCell,
  TableContainer, TableHead, TableRow, Paper, Container, Grid, Button} from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import CloseIcon from '@material-ui/icons/Close';
import axios from 'axios'
import { ContactlessOutlined } from '@material-ui/icons';
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
  
  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="left" ref={ref} {...props} />;
  });
  
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
          </Toolbar>
        </AppBar>
        <Container fixed className={classes.container}>
          <Grid container spacing={3}>
            <Grid item xs={8}>
              <TableContainer component={Paper}>
                <Table className={classes.table} size="small">
                  <TableHead>
                    <Button variant='outlined' color="primary" onClick={deleteProducts}>wyczyść koszyk</Button>
                  </TableHead>
                  <TableBody>
                  {products.length !== 0 ? products.map(({product, quantity}) => (
                    <TableRow key={product.id}>
                      <TableCell component="th" scope="row">
                        <img src={product.image} alt="" width="100" height="100"></img>
                      </TableCell>
                      <TableCell align="left">
                        {product.brand + " " + product.name}
                      </TableCell>
                      <TableCell align="right">
                        {product.price + " zł"}
                      </TableCell>
                      <TableCell align="right">
                        {"Ilość: " + quantity}
                      </TableCell>
                      <TableCell align="center">
                        <IconButton onClick={() => removeItemFromBasket(product.id)} ><DeleteIcon/></IconButton>
                      </TableCell>
                    </TableRow>
                  )) : <TableRow><BasketEmptyPlaceholder/></TableRow>}
                  </TableBody>
                </Table>
              </TableContainer>
            </Grid>
            <Grid item xs={4}>
              <Paper elevation={3}>
                Cena całkowita
                <Typography>{" " + priceTotal + "zł"}</Typography>
                {checkout ? (<PayPalPayment price={priceTotal} />) : ( <Typography><Button variant="outlined" color="primary" onClick={() => {setCheckout(true);}}>Kup</Button></Typography>)}
                    </Paper>
                  </Grid>
                </Grid>
        </Container>
    </div>
  )
}

export default Basket
