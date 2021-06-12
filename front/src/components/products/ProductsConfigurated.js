import React from 'react';
import { makeStyles, Card, CardActionArea, CardActions, CardContent, CardMedia, Button, Typography} from '@material-ui/core';

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
      paddingTop: 15,
      margin: 'auto',
      height: 150,
      width: '80%',
      objectFit: 'contain',
    },
    cardContent: {
      minHeight: 180,
    },
    textSecondary: {
      fontSize: "0.7rem",
    },
    textPrimary: {
      fontSize: "1rem",
    },
}));

//productDetails as a array, that gets converted here to a list or something
export default function ProductsConfigurated({image, productName, detail0, detail1, detail2, detail3, price}) {
  const classes = useStyles();

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
          <Typography className={classes.textPrimary} gutterBottom component="h5" variant="body1">
            {productName}
          </Typography>
          <Typography className={classes.textSecondary} variant="caption" color="textSecondary" component="h2">
            {detail0}<br/>
            {detail1}<br/>
            {detail2}<br/>
            {detail3}
          </Typography>
        </CardContent>
        </CardActionArea>
        <CardActions>
          <Button
            disableFocusRipple
            disableRipple
            variant="outlined"
            className={classes.button}
            color="primary">
              { new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(price) }
          </Button>
        </CardActions>
    </Card>
  )
}

