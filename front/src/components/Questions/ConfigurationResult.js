import React, {useState, useEffect} from 'react'
import { Grid, Typography, Tabs, Button } from '@material-ui/core';
import axios from 'axios';

import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import ProductsConfigurated from '../products/ProductsConfigurated';
import ConfigurationPlaceholder from './ConfigurationPlaceholder';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import { useAuth } from "../../AuthContext";
import { useHistory } from "react-router-dom";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box p={3}>
          <Typography component="span">{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.paper,
  },
}));

function getPriceOptionText(priceOption) {
  var text = "";

  switch(priceOption) {
    case 0:
      text = "Tańsze rozwiązanie";
      break;
    case 1:
      text = "Dopasowane rozwiązanie";
      break;
    case 2:
      text = "Droższe rozwiązanie";
      break;
    default:
      text = "Wygenerowane rozwiązanie";
      break;
  }
  return text;
}

function ConfigurationResult({workloadType, cpuPref, gpuPref, budget, setActiveStep}) {
  const [configurations, setConfigurations] = useState([]);
  const numberOfGrids = workloadType === "office" ? 4 : 3;
  const classes = useStyles();
  const [value, setValue] = useState(0);
  const { currentUser } = useAuth();
  let history = useHistory();

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  useEffect(() => {
    axios.get("http://localhost:8080/comp/form?"
      + "type="
      + workloadType
      + "&cpu=" 
      + cpuPref
      + "&gpu=" 
      + gpuPref
      + "&price=" 
      + budget
    ).then(res => {
      setConfigurations(res.data);
      if(res.data.length > 0) {
        setValue(res.data.length === 3 ? 1 : 0)
      }
    });
  }, [])

  const addConfigurationToCart_ = () => {
    if(currentUser) {
      const config = configurations[value];
      for(var key in config) {
        var keyCopy = key;
        if(keyCopy !== "priceOption" && keyCopy !== "totalPrice") {
          axios.post("http://localhost:8080/cart/addItem?"
            + "email="
            + currentUser.email
            + "&productId="
            + config[key].product.id
            ).then(res => {
              console.log("Dodano pomyślnie do koszyka");
              dispatchEvent(new Event('cartUpdate'));
            }).catch(() => {
              console.log("exception in post method");
            });
        }
      }
    } else {
      history.push("/logowanie");
    }
  }

  const addConfigurationToCart = () => {
    const cartString = localStorage.getItem("cart");
    var cartArray = [];
    if(cartString) {
      cartArray = JSON.parse(cartString);
    }
    var selectedConfiguration = configurations[value];
    //iterate over cartArray and add the current configuration products
    for(var it in selectedConfiguration) {
      const itCopy = it;
      if(itCopy !== "priceOption" && itCopy !== "totalPrice") {
        var i = cartArray.findIndex((el) => (el.id === selectedConfiguration[itCopy].product.id));
        if(i > -1) {
          cartArray[i].quantity++;
        } else {
          cartArray.push({id: selectedConfiguration[itCopy].product.id, quantity: 1});
        }
      }
    }
    localStorage.setItem("cart", JSON.stringify(cartArray));
    window.dispatchEvent(new Event("storage"));
  };

  return (
    <div>
      {configurations.length === 0 ? <ConfigurationPlaceholder setActiveStep={setActiveStep}/> : (<><AppBar position="static">
        <Tabs variant="fullWidth" value={value} onChange={handleChange} aria-label="simple tabs example">
        {configurations.map(({priceOption}, index) => ( <Tab key={index} label={getPriceOptionText(priceOption)} {...a11yProps(priceOption)} /> ))}
        </Tabs>
      </AppBar>
        {configurations.map(({ cpu, gpu, cooler, motherboard, psu, ram, storage, computerCase, totalPrice }, index) => (
        <TabPanel key={index} value={value} index={index}>
          <Grid container spacing={3} direction="row" justify="space-between" alignItems="center">
          <Grid item xs={12}>
              <Button
                variant="contained" size="large" color="primary" style={{display: 'flex', margin: "auto", textTransform: 'none'}}
                endIcon={<AddShoppingCartIcon />} fullWidth
                onClick={()=>addConfigurationToCart_()}
              >
                Dodaj cały zestaw do koszyka { new Intl.NumberFormat('pl-PL', { style: 'currency', currency: 'PLN' }).format(totalPrice) }
              </Button>
          </Grid> 
          <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ cpu.product.brand + " " + cpu.product.name + " " }
              image={cpu.product.image}
              price={cpu.product.price}
              detail0={"Socket: " + cpu.socket}
              detail1={"Taktowanie: " + cpu.coreClock + " GHz"}
              detail2={"Liczba rdzeni (wątków): " + cpu.cores + " (" + (cpu.smt ? cpu.cores * 2 : cpu.cores) + ")" }
              detail3={"TDP: " + cpu.tdpW + " W"}
              productID={cpu.product.id}
            />
          </Grid>
            {workloadType==='office' ? (<></>) : 
            (<>
            <Grid item xs={numberOfGrids}>
              <ProductsConfigurated
                productName={ cooler.product.brand + " " + cooler.product.name + " " }
                image={cooler.product.image}
                price={cooler.product.price}
                detail0={"Maksymalny poziom hałasu: " + cooler.noiseLevelDB + " dB"}
                detail1={"Kompatybilność z gniazdem: " + (cooler.workstation ? "sTRX4" : "2066, 1151, 1200, AM4")}
                detail2={"Typ chłodzenia: " + (cooler.air ? "Powietrzne" : "AIO")}
                productID={cooler.product.id}/>
              </Grid>
              <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
                productName={ gpu.product.brand + " " + gpu.product.name + " " }
                image={gpu.product.image}
                price={gpu.product.price}
                detail0={ "Układ graficzny: " + gpu.chipset }
                detail1={ "Taktowanie rdzenia: " + gpu.coreClockMHZ + " MHz" }
                detail2={ "Pamięć: " + gpu.memoryGB + " GB" }
                detail3={ "Długość: " + gpu.lengthMM + " mm" }
                productID={gpu.product.id}
              /> </Grid>
              </>)}
              <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ motherboard.product.brand + " " + motherboard.product.name + " " }
              image={motherboard.product.image}
              price={motherboard.product.price}
              detail0={ "Socket: " + motherboard.socket }
              detail1={ "Chipset: " + motherboard.chipset }
              detail2={ "Format: " + motherboard.formFactor }
              productID={motherboard.product.id}
            />
            </Grid>
            <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ ram.product.brand + " " + ram.product.name + " " }
              image={ram.product.image}
              price={ram.product.price}
              detail0={ "Taktowanie: " + ram.speed + " MHz" }
              detail1={ "Opóżnienie: CL" + ram.cl }
              detail2={ "Pojemność całkowita: " + (ram.moduleCapacity * ram.modulesCount) + " GB" }
              detail3={ "Ilość modułów: " + ram.modulesCount }
              productID={ram.product.id}
            />
            </Grid>
            <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ storage.product.brand + " " + storage.product.name + " " }
              image={storage.product.image}
              price={storage.product.price}
              detail0={"Pojemność: " + storage.capacityGB + " GB" }
              detail1={"Typ: " + storage.type }
              detail2={"Interfejs: " + storage.interface_ }
              detail3={"Format: " + storage.formFactor }
              productID={storage.product.id}
            />
            </Grid>
            <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ psu.product.brand + " " + psu.product.name + " " }
              image={psu.product.image}
              price={psu.product.price}
              detail0={"Moc: " + psu.wattage + " W"}
              detail1={"Format: " + psu.formFactor }
              detail2={"Sprawność: " + psu.efficiencyRating }
              detail3={"Typ okablowania: " + psu.modular }
              productID={psu.product.id}
            /></Grid>
            <Grid item xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ computerCase.product.brand + " " + computerCase.product.name + " " }
              image={computerCase.product.image}
              price={computerCase.product.price}
              detail0={"Typ: " + computerCase.type }
              detail1={"Panel boczny: " +  computerCase.side_Panel_Window }
              detail2={"Standard zasilacza: " + computerCase.power_Supply_Standard }
              detail3={"Maksymaly wymiar płyty głównej: " + computerCase.max_Motherboard_Size }
              productID={computerCase.product.id}
            />
            </Grid>
            </Grid>
      </TabPanel>
      ))}
      </>)}
    </div>
  );
}

export default ConfigurationResult
