package com.pcc.pc_configurator;

import com.pcc.pc_configurator.DTO.*;
import com.pcc.pc_configurator.entities.*;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

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
                map().setProduct(source.getProduct());
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
                map().setProduct(source.getProduct());
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
                map().setProduct(source.getProduct());
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
                map().setProduct(source.getProduct());
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
                map().setProduct(source.getProduct());
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
                map().setImage(source.getImage());
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
                map().setProduct(source.getProduct());
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
                map().setProduct(source.getProduct());
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
                map().setProduct(source.getProduct());
            }
        });
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setEmail(source.getEmail());
                map().setUsername(source.getUsername());
                map().setPassword(source.getPassword());
            }
        });
        modelMapper.addMappings(new PropertyMap<Order_, OrderDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setDate(source.getDate());
                map().setUser(source.getUser());
                map().setStatus(source.getStatus());
            }
        });
        modelMapper.addMappings(new PropertyMap<OrderList, OrderListDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setProductId(source.getProduct().getId());
                map().setBrand(source.getProduct().getBrand());
                map().setName(source.getProduct().getName());
                map().setPrice(source.getProduct().getPrice());
                map().setProductQuantity(source.getProduct().getQuantity());
                map().setImage(source.getProduct().getImage());
                map().setOrderId(source.getOrder_().getId());
                map().setDate(source.getOrder_().getDate());
                map().setEmail(source.getOrder_().getUser().getEmail());
                map().setUsername(source.getOrder_().getUser().getUsername());
                map().setPassword(source.getOrder_().getUser().getPassword());
                map().setQuantity(source.getQuantity());
            }
        });
        modelMapper.addMappings(new PropertyMap<Cart, CartDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setUser(source.getUser());
                map().setProduct(source.getProduct());
                map().setQuantity(source.getQuantity());
            }
        });
        return modelMapper;
    }
}
