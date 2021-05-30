import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function GpuPicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/gpu?page=0&size=10&sortBy=product.brand&sortingOrder=desc").then(res => {
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
        {products.map(({product, memoryGB, chipset, coreClockMHZ, boostClockMHZ, lengthMM  }, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name  }
              price={Number(product.price).toFixed(2)}
              detail0={"Układ graficzny: " + chipset }
              detail1={"Taktowanie rdzenia: " + coreClockMHZ + " MHz (" + boostClockMHZ + " MHz w trybie Boost)"}
              detail2={"Pamięć: " + memoryGB + " GB" }
              detail3={"Długość: " + lengthMM + " mm" }
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}
export default GpuPicker;