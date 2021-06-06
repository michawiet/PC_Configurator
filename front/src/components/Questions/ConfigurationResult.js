import { Paper, Grid,Typography } from '@material-ui/core';
import React, {useState, useEffect} from 'react'
import axios from 'axios';
import ProdukcsConfigurated from '../products/ProductsConfigurated'

function ConfigurationResult({workloadType, cpuPref, gpuPref, budget}) {
  const [configurations, setConfigurations] = useState([]);
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
      <Grid container alignItems="center" spacing={3}>
      <Grid item  xs={4}><Typography   align="center">Konfiguracja tańsza </Typography></Grid>
      <Grid item  xs={4}>konfiguracja 2</Grid>
      <Grid item  xs={4}>konfiguracja 3</Grid>
      {configurations.map(({ cpu, gpu, cooler , motherboard, psu, ram, storage,computerCase,totalPrice}, index) => (      
            
          <Grid item key={index} xs={4}>
            {totalPrice}
            <ProdukcsConfigurated
              productName={ cpu.product.brand + " " + cpu.product.name + " " }
              image={cpu.product.image}
              price={Number(cpu.product.price).toFixed(2)}
              detail0={"Socket: " + cpu.socket}
              detail1={"Taktowanie: " + cpu.coreClock + " GHz"}
              detail2={"Liczba rdzeni (wątków): " + cpu.cores + " (" + (cpu.smt ? cpu.cores * 2 : cpu.cores) + ")" }
              detail3={"TDP: " + cpu.tdpW + " W"}
              productID={cpu.product.id}
            />
            {workloadType==='office'? " ": (<> <ProdukcsConfigurated
                productName={ cooler.product.brand + " " + cooler.product.name + " " }
                image={cooler.product.image}
                price={Number(cooler.product.price).toFixed(2)}
                detail0={"Maksymalny poziom hałasu: " + cooler.noiseLevelDB + " dB"}
                detail1={"Kompatybilność z gniazdem: " + (cooler.workstation ? "sTRX4" : "2066, 1151, 1200, AM4")}
                detail2={"Typ chłodzenia: " + (cooler.air ? "Powietrzne" : "AIO")}
                productID={cooler.product.id}
              />
              <ProdukcsConfigurated
                productName={ gpu.product.brand + " " + gpu.product.name + " " }
                image={gpu.product.image}
                price={Number(gpu.product.price).toFixed(2)}
                detail0={ "Układ graficzny: " + gpu.chipset }
                detail1={ "Taktowanie rdzenia: " + gpu.coreClockMHZ + " MHz (" + gpu.boostClockMHZ + " MHz w trybie Boost)" }
                detail2={ "Pamięć: " + gpu.memoryGB + " GB" }
                detail3={ "Długość: " + gpu.lengthMM + " mm" }
                productID={gpu.product.id}
              /> </>)}
              <ProdukcsConfigurated
              productName={ motherboard.product.brand + " " + motherboard.product.name + " " }
              image={motherboard.product.image}
              price={Number(motherboard.product.price).toFixed(2)}
              detail0={ "Socket: " + motherboard.socket }
              detail1={ "Chipset: " + motherboard.chipset }
              detail2={ "Format: " + motherboard.formFactor }
              productID={motherboard.product.id}
            />
            <ProdukcsConfigurated
              productName={ ram.product.brand + " " + ram.product.name + " " }
              image={ram.product.image}
              price={Number(ram.product.price).toFixed(2)}
              detail0={ "Taktowanie: " + ram.speed + " MHz" }
              detail1={ "Opóżnienie: CL" + ram.cl }
              detail2={ "Pojemność całkowita: " + (ram.moduleCapacity * ram.modulesCount) + " GB" }
              detail3={ "Ilość modułów: " + ram.modulesCount }
              productID={ram.product.id}
            />
            <ProdukcsConfigurated
              productName={ storage.product.brand + " " + storage.product.name + " " }
              image={storage.product.image}
              price={Number(storage.product.price).toFixed(2)}
              detail0={"Pojemność: " + storage.capacityGB + " GB" }
              detail1={"Typ: " + storage.type }
              detail2={"Interfejs: " + storage.interface_ }
              detail3={"Format: " + storage.formFactor }
              productID={storage.product.id}
            />
            <ProdukcsConfigurated
              productName={ psu.product.brand + " " + psu.product.name + " " }
              image={psu.product.image}
              price={Number(psu.product.price).toFixed(2)}
              detail0={"Moc: " + psu.wattage + " W"}
              detail1={"Format: " + psu.formFactor }
              detail2={"Sprawność: " + psu.efficiencyRating }
              detail3={"Typ okablowania: " + psu.modular }
              productID={psu.product.id}
            />
            <ProdukcsConfigurated
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
          
      ))}
        </Grid>
    </div>
  )
}

export default ConfigurationResult
