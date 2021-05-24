import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function PsuPicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/psu").then(res => {
        console.log(res);
        setProducts(res.data);
      });
    };
  
    useEffect(() => {
      fetchProducts();
    }, []);

  return (
    <div>
      <Grid container spacing={3}>
        {products.map(({product, modular, wattage, formFactor, efficiencyRating }, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name }
              price={Number(product.price).toFixed(2)}
              detail0={"Moc: " + wattage + " W"}
              detail1={"Format: " + formFactor }
              detail2={"Sprawność: " + efficiencyRating }
              detail3={"Typ okablowania: " + modular }
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}
export default PsuPicker;