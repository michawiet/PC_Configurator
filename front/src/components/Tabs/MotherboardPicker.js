import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function CoolerPicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/motherboard").then(res => {
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
        {products.map(({product, formFactor, chipset, socket}, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name }
              price={Number(product.price).toFixed(2)}
              detail0={"Socket: " + socket }
              detail1={"Chipset: " + chipset }
              detail2={"Format: " + formFactor }
            
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}

export default CoolerPicker
