import React, {useState, useEffect} from 'react'
import { Paper, Grid, Typography, Tabs } from '@material-ui/core';
import axios from 'axios';

import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import ProductsConfigurated from '../products/ProductsConfigurated';
import ConfigurationPlaceholder from './ConfigurationPlaceholder';

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
          <Typography>{children}</Typography>
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
  var text = "Rozwiązanie";

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
  }
  return text;
}

function ConfigurationResult({workloadType, cpuPref, gpuPref, budget}) {
  const [configurations, setConfigurations] = useState([]);
  const [numberOfGrids, setNumberOfGrids] = useState(workloadType === "office" ? 4 : 3 );
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

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
      console.log(res.data);
      setConfigurations(res.data);
    });
  }, [])

  return (
    <div>
      {configurations.length === 0 ? <ConfigurationPlaceholder/> : (<><AppBar position="static">
        <Tabs variant="fullWidth" value={value} onChange={handleChange} aria-label="simple tabs example">
        {configurations.map(({priceOption}) => ( <Tab label={getPriceOptionText(priceOption)} {...a11yProps(priceOption)} /> ))}
        </Tabs>
      </AppBar>
        {configurations.map(({ cpu, gpu, cooler, motherboard, psu, ram, storage, computerCase, totalPrice, priceOption}, index) => (
        <TabPanel value={value} index={index}>
          <Grid container>
          <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ cpu.product.brand + " " + cpu.product.name + " " }
              image={cpu.product.image}
              price={Number(cpu.product.price).toFixed(2)}
              detail0={"Socket: " + cpu.socket}
              detail1={"Taktowanie: " + cpu.coreClock + " GHz"}
              detail2={"Liczba rdzeni (wątków): " + cpu.cores + " (" + (cpu.smt ? cpu.cores * 2 : cpu.cores) + ")" }
              detail3={"TDP: " + cpu.tdpW + " W"}
              productID={cpu.product.id}
            />
          </Grid>
            {workloadType==='office' ? (<></>) : 
            (<>
            <Grid item key={index} xs={numberOfGrids}>
              <ProductsConfigurated
                productName={ cooler.product.brand + " " + cooler.product.name + " " }
                image={cooler.product.image}
                price={Number(cooler.product.price).toFixed(2)}
                detail0={"Maksymalny poziom hałasu: " + cooler.noiseLevelDB + " dB"}
                detail1={"Kompatybilność z gniazdem: " + (cooler.workstation ? "sTRX4" : "2066, 1151, 1200, AM4")}
                detail2={"Typ chłodzenia: " + (cooler.air ? "Powietrzne" : "AIO")}
                productID={cooler.product.id}/>
              </Grid>
              <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
                productName={ gpu.product.brand + " " + gpu.product.name + " " }
                image={gpu.product.image}
                price={Number(gpu.product.price).toFixed(2)}
                detail0={ "Układ graficzny: " + gpu.chipset }
                detail1={ "Taktowanie rdzenia: " + gpu.coreClockMHZ + " MHz (" + gpu.boostClockMHZ + " MHz w trybie Boost)" }
                detail2={ "Pamięć: " + gpu.memoryGB + " GB" }
                detail3={ "Długość: " + gpu.lengthMM + " mm" }
                productID={gpu.product.id}
              /> </Grid>
              </>)}
              <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ motherboard.product.brand + " " + motherboard.product.name + " " }
              image={motherboard.product.image}
              price={Number(motherboard.product.price).toFixed(2)}
              detail0={ "Socket: " + motherboard.socket }
              detail1={ "Chipset: " + motherboard.chipset }
              detail2={ "Format: " + motherboard.formFactor }
              productID={motherboard.product.id}
            />
            </Grid>
            <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ ram.product.brand + " " + ram.product.name + " " }
              image={ram.product.image}
              price={Number(ram.product.price).toFixed(2)}
              detail0={ "Taktowanie: " + ram.speed + " MHz" }
              detail1={ "Opóżnienie: CL" + ram.cl }
              detail2={ "Pojemność całkowita: " + (ram.moduleCapacity * ram.modulesCount) + " GB" }
              detail3={ "Ilość modułów: " + ram.modulesCount }
              productID={ram.product.id}
            />
            </Grid>
            <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ storage.product.brand + " " + storage.product.name + " " }
              image={storage.product.image}
              price={Number(storage.product.price).toFixed(2)}
              detail0={"Pojemność: " + storage.capacityGB + " GB" }
              detail1={"Typ: " + storage.type }
              detail2={"Interfejs: " + storage.interface_ }
              detail3={"Format: " + storage.formFactor }
              productID={storage.product.id}
            />
            </Grid>
            <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ psu.product.brand + " " + psu.product.name + " " }
              image={psu.product.image}
              price={Number(psu.product.price).toFixed(2)}
              detail0={"Moc: " + psu.wattage + " W"}
              detail1={"Format: " + psu.formFactor }
              detail2={"Sprawność: " + psu.efficiencyRating }
              detail3={"Typ okablowania: " + psu.modular }
              productID={psu.product.id}
            /></Grid>
            <Grid item key={index} xs={numberOfGrids}>
            <ProductsConfigurated
              productName={ computerCase.product.brand + " " + computerCase.product.name + " " }
              image={computerCase.product.image}
              price={Number(computerCase.product.price).toFixed(2)}
              detail0={"Typ: " + computerCase.type }
              detail1={"Panel boczny: " +  computerCase.side_Panel_Window }
              detail2={"Standard zasilacza: " + computerCase.power_Supply_Standard }
              detail3={"Maksymaly wymiar płyty głównej: " + computerCase.max_Motherboard_Size }
              productID={computerCase.product.id}
            />
            </Grid>
            </Grid>
      </TabPanel>))}
      </>)}
    </div>
  );
}

export default ConfigurationResult
