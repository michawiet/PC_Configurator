import React, {useState}  from 'react';
import { makeStyles, Card, CardActionArea, CardActions, CardContent, CardMedia, Button, Typography} from '@material-ui/core';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';

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
      minHeight: 150,
    }
}));

//productDetails as a array, that gets converted here to a list or something
export default function VerticalProductCard({image, productName, detail0, detail1, detail2, detail3, price, href}) {
  const classes = useStyles();

  const [open, setOpen] = useState(false);

  const handleClickOpen = () => {
    setOpen(true);
    //tu sie dialog otworzy
  };

  return (
    <Card className={classes.root}>
      <CardActionArea  onClick={handleClickOpen}>
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
          <Button variant="contained" className={classes.button} endIcon={<AddShoppingCartIcon />} color="primary">
            {price} zł
          </Button>
        </CardActions>
    </Card>
  )
}

