import { Grid, makeStyles } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import VerticalProductCard from '../products/VerticalProductCard'
import axios from 'axios';

function CoolerPicker() {
  const [products, setProducts] = useState([]);
  
    const fetchProducts = () => {
      axios.get("http://localhost:8080/products/cooler?page=0&size=10&sortBy=product.brand&sortingOrder=desc").then(res => {
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
        {products.map(({product, noiseLevelDB, tier, workstation, air}) => (
          <Grid item xs={4}>
            <VerticalProductCard
              productName={product.brand + " " + product.name}
              price={Number(product.price).toFixed(2)}
              detail0={"Maksymalny poziom hałasu: " + noiseLevelDB + " dB"}
              detail1={"Kompatybilność z gniazdem: " + (workstation ? "sTRX4" : "2066, 1151, 1200, AM4")}
              detail2={"Typ chłodzenia: " + (air ? "Powietrzne" : "AIO")}
            />
          </Grid>
        ))}
      </Grid>
    </div>
  )
}

export default CoolerPicker
