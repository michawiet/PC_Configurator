import React, { useState }  from 'react';
import { makeStyles, Card, CardActionArea, CardActions, CardContent, CardMedia, Button, Typography } from '@material-ui/core';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import axios from 'axios';
import { useAuth } from "../../AuthContext";
import { useHistory } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    root: {
      maxWidth: 400,
      boxShadow: theme.shadows[1],
      '&:hover': {
        boxShadow: theme.shadows[6],
      },
    },
    button: {
      marginLeft: 'auto !important',
    },
    cardMedia: {
      paddingTop: 20,
      margin: 'auto',
      height: 220,
      width: '80%',
      objectFit: 'contain',
    },
    cardContent: {
      minHeight: 165,
    }
}));

//productDetails as a array, that gets converted here to a list or something
export default function VerticalProductCard({image, productName, detail0, detail1, detail2, detail3, price, productID}) {
  const classes = useStyles();
  const { currentUser } = useAuth();
  let history = useHistory();
  
  const addProductToCart = () => {
    if(currentUser) {
      //axios post
      currentUser.getIdToken(/* forceRefresh */ true).then(function(idToken) {
        axios.post("http://localhost:8080/cart/addItem?" 
          + "token="
          + idToken
          + "&productId=" 
          + productID
        ).then(res => {
          dispatchEvent(new Event('cartUpdate'));
        })
      }).catch(function(error) {
        // Handle error
      });
      
    } else {
      history.push("/logowanie");
    }
  }

  const addToLocalStorage = () => {
    var basketString = localStorage.getItem("cart");
    var basketItems = [];
    
    if(basketString) {
      basketItems = JSON.parse(basketString);
    }
    
    var exists = false;
    for(var product of basketItems) { //check if item exists
      if(product.id === productID) {
        product.quantity++;
        exists = true;
      }
    }
    if(!exists) {
      basketItems.push({id: productID, quantity: 1});
    }
    var string = JSON.stringify(basketItems);
    localStorage.setItem("cart", string);
    window.dispatchEvent(new Event("storage"));
  };

  return (
    <Card className={classes.root}>
      <CardActionArea>
        <CardMedia 
          className={classes.cardMedia}
          component="img"
          src={image}
        >
        </CardMedia>
        <CardContent className={classes.cardContent}>
          <Typography gutterBottom component="h5" variant="body1">
            {productName}
          </Typography>
          <Typography variant="caption" color="textSecondary" component="h2">
            {detail0}<br/>
            {detail1}<br/>
            {detail2}<br/>
            {detail3}
          </Typography>
        </CardContent>
        </CardActionArea>
        <CardActions>
          <Button
            variant="contained"
            className={classes.button}
            endIcon={<AddShoppingCartIcon />}
            onClick={addToLocalStorage, addProductToCart}
            color="primary">
              { new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(price) }
          </Button>
        </CardActions>
    </Card>
  )
}

