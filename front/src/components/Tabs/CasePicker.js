import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function CasePicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/case").then(res => {
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
        {products.map(({product, type, side_Panel_Window, max_Motherboard_Size, power_Supply_Standard}, index) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={ product.brand + " " + product.name }
              price={Number(product.price).toFixed(2)}
              detail0={"Typ: " + type }
              detail1={"Panel boczny: " +  side_Panel_Window }
              detail2={"Standard zasilacza: " + power_Supply_Standard }
              detail3={"Maksymaly wymiar płyty głównej: " + max_Motherboard_Size }
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}
export default CasePicker;