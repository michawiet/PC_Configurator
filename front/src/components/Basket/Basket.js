import React, {useState, useEffect} from 'react';
import { useHistory } from "react-router-dom";
import { makeStyles, Dialog, AppBar, Toolbar, IconButton, Typography, Slide, Table, TableBody, TableCell,
  TableContainer, TableHead, TableRow, Paper, Container, Grid, Button} from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import CloseIcon from '@material-ui/icons/Close';
import axios from 'axios'
import { ContactlessOutlined } from '@material-ui/icons';

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

  useEffect(() => {
    //get basket from local storage
    const basketString = localStorage.getItem("basket");
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
        console.log(productsArr);
        setProducts(productsArr);
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
  };
  const removeItemFromBasket = () =>{

  };
  var totalPrice = 0 ;
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
                  {/* TODO: usunąć table head */}
                  <TableHead>
                    <TableRow>
                      <TableCell>Zdjęcie:</TableCell>
                      <TableCell align="right">Nazwa:</TableCell>
                      <TableCell align="right">Cena:</TableCell>
                      <TableCell align="right">Ilość:</TableCell>
                      <TableCell align="right"><Button variant='outlined' color="primary" onClick={deleteProducts}>wyczyść koszyk</Button></TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                  {products.map(({product, quantity}) => (
                    <TableRow key={product.id}>
                      <TableCell component="th" scope="row">
                        <img src={product.image} alt="" width="100" height="100"></img>
                      </TableCell>
                      <TableCell align="right">
                        {product.name}
                      </TableCell>
                      <TableCell align="right">
                        {product.price}
                      </TableCell>
                      <TableCell align="right">
                        {quantity}
                        
                      </TableCell>
                      <TableCell align="right">
                        <IconButton onClick={removeItemFromBasket} ><DeleteIcon/></IconButton>
                      </TableCell>
                    </TableRow>
                  ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </Grid>
            <Grid item xs={4}>
              <Paper elevation={3}>
                Tutaj terz XD
                {totalPrice}
                <Button>Kup</Button>
              </Paper>
            </Grid>
          </Grid>
        </Container>
    </div>
  )
}

export default Basket
