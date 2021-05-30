import React from 'react';
import { makeStyles, Card, CardActionArea, CardActions, CardContent, CardMedia, Button, Typography, List, ListItem, ListItemText, Grid } from '@material-ui/core';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import fanImage from '../../icons/fan.png'

const useStyles = makeStyles({
    root: {
        maxWidth: 400,
    },
    button: {
      marginLeft: 'auto !important',
    },
    image: {
      paddingTop: 10,
      margin: "auto",
      maxWidth: 200,
      maxHeight: 210,
    },
});
//productDetails as a array, that gets converted here to a list or something
export default function VerticalProductCard({image, productName, detail0, detail1, detail2, detail3, price, href}) {
  const classes = useStyles();

  return (
    <Card className={classes.root}>
      <CardActionArea>
        <CardMedia 
          className={classes.image}
          component="img"
          image={fanImage}
        />
        <CardContent>
          <Typography component={'span'} gutterBottom variant="h5" component="h2">
            {productName}
          </Typography>
          <Typography component={'span'} variant="caption" color="textSecondary" component="h2">
            {detail0}<br/>
            {detail1}<br/>
            {detail2}<br/>
            {detail3}<br/>
          </Typography>
        </CardContent>
        </CardActionArea>
        <CardActions>
        <p></p>
        <Button className={classes.button} endIcon={<AddShoppingCartIcon />} variant="contained" color="primary">
          {price} zł &nbsp;
        </Button>
        </CardActions>
    </Card>
    );
}

//const withDefaultProps = defaultProps