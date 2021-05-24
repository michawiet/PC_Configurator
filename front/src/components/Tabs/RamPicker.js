import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function RamPicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/ram").then(res => {
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
        {products.map(({product, moduleCapacity, speed, modulesCount, cl }, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name + " (" + modulesCount + " x " + moduleCapacity + " GB)" }
              price={Number(product.price).toFixed(2)}
              detail0={"Taktowanie  : " + speed + " MHz" }
              detail1={"Opóżnienie: CL" + cl }
              detail2={"Pojemność całkowita: " + (moduleCapacity * modulesCount) + " GB" }
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}

export default RamPicker
