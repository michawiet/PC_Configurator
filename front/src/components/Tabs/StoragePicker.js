import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function StoragePicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/storage?page=0&size=10&sortBy=product.brand&sortingOrder=desc").then(res => {
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
        {products.map(({product,capacityGB, type, formFactor, interface_  }, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name  }
              price={Number(product.price).toFixed(2)}
              detail0={"Pojemność: " + capacityGB + " GB" }
              detail1={"Typ: " + type }
              detail2={"Interfejs: " + interface_ }
              detail3={"Format: " + formFactor }
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}
export default StoragePicker;