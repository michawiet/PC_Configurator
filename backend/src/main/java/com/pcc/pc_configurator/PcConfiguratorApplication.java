package com.pcc.pc_configurator;

import com.pcc.pc_configurator.DTO.*;
import com.pcc.pc_configurator.entities.*;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Map;

@SpringBootApplication
public class PcConfiguratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcConfiguratorApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Cpu, CpuDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setSocket(source.getSocket());
                map().setCores(source.getCores());
                map().setSmt(source.isSmt());
                map().setIntegratedGPU(source.isIntegrated_GPU());
                map().setTdpW(source.getTdp_W());
                map().setStPref(source.getSt_Pref());
                map().setMtPref(source.getMt_Pref());
                map().setCoreClock(source.getCore_Clock());
                map().setBoostClock(source.getBoost_Clock());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Gpu, GpuDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setChipset(source.getChipset());
                map().setMemoryGB(source.getMemory_GB());
                map().setCoreClockMHZ(source.getCore_Clock_MHZ());
                map().setBoostClockMHZ(source.getBoost_Clock_MHZ());
                map().setLengthMM(source.getLength_MM());
                map().setTdp(source.getTdp());
                map().setRecommendedPsuWatts(source.getRecommended_Psu_Watts());
                map().setPerformance(source.getPerformance());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Case_, CaseDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPower_Supply_Standard(source.getPower_Supply_Standard());
                map().setMax_Motherboard_Size(source.getMax_Motherboard_Size());
                map().setType(source.getType());
                map().setSide_Panel_Window(source.getSide_Panel_Window());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Cooler, CoolerDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTier(source.getTier());
                map().setNoiseLevelDB(source.getNoise_Level_DB());
                map().setAir(source.isIs_Air());
                map().setWorkstation(source.isIs_Workstation());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Motherboard, MotherboardDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTier(source.getTier());
                map().setChipset(source.getChipset());
                map().setSocket(source.getSocket());
                map().setFormFactor(source.getForm_Factor());
                map().setMemorySlots(source.getMemory_Slots());
                map().setMemoryMaxGB(source.getMemory_Max_GB());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Product, ProductsDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setBrand(source.getBrand());
                map().setName(source.getName());
                map().setPrice(source.getPrice());
                map().setQuantity(source.getQuantity());
            }
        });
        modelMapper.addMappings(new PropertyMap<Psu, PsuDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTier(source.getTier());
                map().setFormFactor(source.getForm_Factor());
                map().setEfficiencyRating(source.getEfficiency_Rating());
                map().setWattage(source.getWattage());
                map().setModular(source.getModular());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Ram, RamDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setSpeed(source.getSpeed());
                map().setModulesCount(source.getModules_Count());
                map().setModuleCapacity(source.getModule_Capacity_Gb());
                map().setFwLatencyNs(source.getFw_Latency_Ns());
                map().setCl(source.getCl());
                map().setProductFK(source.getProduct_FK());
            }
        });
        modelMapper.addMappings(new PropertyMap<Storage, StorageDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCapacityGB(source.getCapacity_GB());
                map().setTier(source.getTier());
                map().setType(source.getType());
                map().setFormFactor(source.getForm_Factor());
                map().setInterface_(source.getStorage_interface());
                map().setProductFK(source.getProduct_FK());
            }
        });
        return modelMapper;
    }
}
