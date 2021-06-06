package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.*;
import com.pcc.pc_configurator.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comp")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ComputerController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    CoolerRepository coolerRepository;
    @Autowired
    CpuRepository cpuRepository;
    @Autowired
    GpuRepository gpuRepository;
    @Autowired
    MotherboardRepository motherboardRepository;
    @Autowired
    PsuRepository psuRepository;
    @Autowired
    RamRepository ramRepository;
    @Autowired
    StorageRepository storageRepository;

    @Autowired
    ModelMapper modelMapper;

    final String OFFICE_WORKLOAD = "office";
    final String PHOTO_WORKLOAD = "photo-editing";
    final String VIDEO_WORKLOAD = "video-editing";
    final String GAMING_WORKLOAD = "gaming";
    final String RENDERING_WORKLOAD = "3d-rendering";

    final float VARIATION = 0.2f;

    List<CaseDTO> caseDtoList = new ArrayList<>();
    List<CoolerDTO> coolerDtoList = new ArrayList<>();
    List<CpuDTO> cpuDtoList = new ArrayList<>();
    List<GpuDTO> gpuDtoList = new ArrayList<>();
    List<MotherboardDTO> motherboardDtoList = new ArrayList<>();
    List<PsuDTO> psuDtoList = new ArrayList<>();
    List<RamDTO> ramDtoList = new ArrayList<>();
    List<StorageDTO> storageDtoList = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void fillLists() {
        for (var case_ : caseRepository.findAll()) { caseDtoList.add(modelMapper.map(case_, CaseDTO.class)); }
        for (var cooler : coolerRepository.findAll()) { coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class)); }
        for (var cpu : cpuRepository.findAll()) { cpuDtoList.add(modelMapper.map(cpu, CpuDTO.class)); }
        for (var gpu : gpuRepository.findAll()) { gpuDtoList.add(modelMapper.map(gpu, GpuDTO.class)); }
        for (var motherboard : motherboardRepository.findAll()) { motherboardDtoList.add(modelMapper.map(motherboard, MotherboardDTO.class)); }
        for (var psu : psuRepository.findAll()) { psuDtoList.add(modelMapper.map(psu, PsuDTO.class)); }
        for (var ram : ramRepository.findAll()) { ramDtoList.add(modelMapper.map(ram, RamDTO.class)); }
        for (var storage : storageRepository.findAll()) { storageDtoList.add(modelMapper.map(storage, StorageDTO.class)); }
    }

    private CaseDTO getCase(float minPrice, float maxPrice) throws NoSuchElementException {
        return caseDtoList.stream().filter(p -> (p.getProduct().getPrice() >= minPrice)//price MIN
                && (p.getProduct().getPrice() <= maxPrice)
                && (p.getMax_Motherboard_Size().equals("ATX")))
                .sorted(((o1, o2) -> Float.compare(o1.getProduct().getPrice(), o2.getProduct().getPrice())))
                .findFirst().get();
    }

    private PsuDTO getPsu(float minPrice, float maxPrice) throws NoSuchElementException {
        return psuDtoList.stream()
                .filter(p -> (p.getProduct().getPrice() >= minPrice)//price MIN
                        && (p.getProduct().getPrice() <= maxPrice))
                .sorted(Comparator.comparingDouble(PsuDTO::getTier))
                .findFirst()
                .get();
    }

    private MotherboardDTO getMotherboard(float minPrice, float maxPrice, String cpuSocket) throws NoSuchElementException {
        return motherboardDtoList.stream()
                .filter(p -> p.getSocket().equals(cpuSocket)
                        && (p.getProduct().getPrice() >= minPrice)//price MIN
                        && (p.getProduct().getPrice() <= maxPrice))
                .sorted(Comparator.comparingDouble(MotherboardDTO::getTier))
                .findFirst()
                .get();
    }

    private RamDTO getRam(float minPrice, float maxPrice) {
        return ramDtoList.stream()
                .filter(p -> (p.getProduct().getPrice() >= minPrice)//price MIN
                        && (p.getProduct().getPrice() <= maxPrice))
                .sorted(Comparator.comparingInt(RamDTO::getSpeed))
                .findFirst()
                .get();
    }

    private StorageDTO getStorage(float minPrice, float maxPrice) throws NoSuchElementException {
        return storageDtoList.stream()
                .filter(p -> (p.getProduct().getPrice() >= minPrice)//price MIN
                        && (p.getProduct().getPrice() <= maxPrice))
                .sorted(Comparator.comparingInt(StorageDTO::getTier))
                .findFirst()
                .get();
    }

    private GpuDTO getGpu(float minPrice, float maxPrice, String preferredGpuBrand) throws NoSuchElementException {
        return gpuDtoList.stream()
                .filter(p -> (preferredGpuBrand.equals("any") ? true : p.getChipset().substring(0, p.getChipset().indexOf(' ')).equals(preferredGpuBrand))
                        && (p.getProduct().getPrice() >= minPrice)//price MIN
                        && (p.getProduct().getPrice() <= maxPrice))
                .sorted(Comparator.comparingInt(GpuDTO::getPerformance).reversed())
                .findFirst()
                .get();
    }

    private CoolerDTO getCooler(float minPrice, float maxPrice) throws NoSuchElementException {
        return coolerDtoList.stream()
                .filter(p -> (p.getProduct().getPrice() >= minPrice)//price MIN
                        && (p.getProduct().getPrice() <= maxPrice))
                .sorted(Comparator.comparingInt(CoolerDTO::getTier))
                .findFirst()
                .get();
    }

    private Map<String, Object> getOfficeComputer(Float budget, String preferredCpuBrand) throws NoSuchElementException {
        Map<String, Object> map = new HashMap<>();
        //factors
        final float MIN = budget - (budget * VARIATION);
        final float MAX = budget + (budget * VARIATION);
        final float cpuFactor = 0.3f;
        final float motherboardFactor = 0.167f;
        final float ramFactor = 0.16f;
        final float storageFactor = 0.13f;
        final float psuFactor = 0.113f;
        final float caseFactor = 0.13f;
        //TODO: wypisać je tutaj jako zmienne final
        //find a cpu with integrated graphics of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> p.isIntegratedGPU()
                        && (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * cpuFactor))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * cpuFactor))//brand
                )
                .sorted(Comparator.comparingInt(CpuDTO::getStPref).reversed())
                .findFirst().get();
        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * motherboardFactor, MAX * motherboardFactor, cpu.getSocket());
        //find a ram
        var ram = getRam(MIN * ramFactor, MAX * ramFactor);
        //find a storage
        var storage = getStorage(MIN * storageFactor, MAX * storageFactor);
        //find a psu
        var psu = getPsu(MIN * psuFactor, MAX * psuFactor);
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * caseFactor, MAX * caseFactor);

        map.put("cpu", cpu);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("psu", psu);
        map.put("computerCase", case_);
        return map;
    }

    private Map<String, Object> getGamingComputer(Float budget, String preferredCpuBrand, String preferredGpuBrand) {
        Map<String, Object> map = new HashMap<>();
        //factors
        final float MIN = budget - (budget * VARIATION);
        final float MAX = budget + (budget * VARIATION);
        final float cpuFactor = 0.21f;
        final float coolerFactor = 0.04f;
        final float motherboardFactor = 0.11f;
        final float ramFactor = 0.08f;
        final float storageFactor = 0.1f;
        final float gpuFactor = 0.33f;
        final float psuFactor = 0.06f;
        final float caseFactor = 0.07f;

        //find a cpu of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * cpuFactor))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * cpuFactor)) //brand
                )
                .sorted(Comparator.comparingInt(CpuDTO::getStPref).reversed())
                .findFirst().get();
        //find a cooler
        var cooler = getCooler(MIN * coolerFactor, MAX * coolerFactor);

        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * motherboardFactor, MAX * motherboardFactor, cpu.getSocket());
        //find a ram
        var ram = getRam(MIN * ramFactor, MAX * ramFactor);
        //find a gpu
        var gpu = getGpu(MIN * gpuFactor, MAX * gpuFactor, preferredGpuBrand);
        //find a storage
        var storage = getStorage(MIN * storageFactor, MAX * storageFactor);
        //find a psu
        var psu= getPsu(MIN * psuFactor, MAX * psuFactor);
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * caseFactor, MAX * caseFactor);

        map.put("cpu", cpu);
        map.put("cooler", cooler);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("gpu",gpu);
        map.put("psu", psu);
        map.put("computerCase", case_);
        return map;
    }

    private Map<String, Object> getPhotoComputer(Float budget, String preferredCpuBrand, String preferredGpuBrand) {
        Map<String, Object> map = new HashMap<>();
        //factors
        final float MIN = budget - (budget * VARIATION);
        final float MAX = budget + (budget * VARIATION);
        final float cpuFactor = 0.24f;
        final float coolerFactor = 0.06f;
        final float motherboardFactor = 0.13f;
        final float ramFactor = 0.13f;
        final float storageFactor = 0.12f;
        final float gpuFactor = 0.17f;
        final float psuFactor = 0.08f;
        final float caseFactor = 0.07f;

        //find a cpu of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * cpuFactor))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * cpuFactor)) //brand
                )
                .sorted(Comparator.comparingInt(CpuDTO::getStPref).reversed())
                .findFirst().get();
        //find a cooler of preferred brand
        var cooler = getCooler(MIN * coolerFactor, MAX * coolerFactor);
        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * motherboardFactor, MAX * motherboardFactor, cpu.getSocket());
        //find a ram
        var ram = getRam(MIN * ramFactor, MAX * ramFactor);
        //find a gpu
        var gpu = getGpu(MIN * gpuFactor, MAX * gpuFactor, preferredGpuBrand);
        //find a storage
        var storage = getStorage(MIN * storageFactor, MAX * storageFactor);
        //find a psu
        var psu= getPsu(MIN * psuFactor, MAX * psuFactor);
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * caseFactor, MAX * caseFactor);

        map.put("cpu", cpu);
        map.put("cooler", cooler);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("gpu",gpu);
        map.put("psu", psu);
        map.put("computerCase", case_);
        return map;
    }

    private Map<String, Object> getVideoComputer(Float budget, String preferredCpuBrand, String preferredGpuBrand) {
        Map<String, Object> map = new HashMap<>();
        //factors
        final float MIN = budget - (budget * VARIATION);
        final float MAX = budget + (budget * VARIATION);
        final float cpuFactor = 0.27f;
        final float coolerFactor = 0.04f;
        final float motherboardFactor = 0.1f;
        final float ramFactor = 0.1f;
        final float storageFactor = 0.14f;
        final float gpuFactor = 0.19f;
        final float psuFactor = 0.08f;
        final float caseFactor = 0.08f;

        //find a cpu of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * cpuFactor))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * cpuFactor)) //brand
                )
                .sorted(Comparator.comparingInt(CpuDTO::getMtPref).reversed())
                .findFirst().get();
        //find a cooler of preferred brand
        var cooler = getCooler(MIN * coolerFactor, MAX * coolerFactor);
        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * motherboardFactor, MAX * motherboardFactor, cpu.getSocket());
        //find a ram
        var ram = getRam(MIN * ramFactor, MAX * ramFactor);
        //find a gpu
        var gpu = getGpu(MIN * gpuFactor, MAX * gpuFactor, preferredGpuBrand);
        //find a storage
        var storage = getStorage(MIN * storageFactor, MAX * storageFactor);
        //find a psu
        var psu= getPsu(MIN * psuFactor, MAX * psuFactor);
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * caseFactor, MAX * caseFactor);

        map.put("cpu", cpu);
        map.put("cooler", cooler);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("gpu",gpu);
        map.put("psu", psu);
        map.put("computerCase", case_);
        return map;
    }

    private Map<String, Object> getRenderingComputer(Float budget, String preferredCpuBrand, String preferredGpuBrand) {
        Map<String, Object> map = new HashMap<>();
        //factors
        final float MIN = budget - (budget * VARIATION);
        final float MAX = budget + (budget * VARIATION);
        final float cpuFactor = 0.27f;
        final float coolerFactor = 0.039f;
        final float motherboardFactor = 0.067f;
        final float ramFactor = 0.14f;
        final float storageFactor = 0.17f;
        final float gpuFactor = 0.235f;
        final float psuFactor = 0.059f;
        final float caseFactor = 0.04f;

        //find a cpu of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * cpuFactor))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * cpuFactor)) //brand
                )
                .sorted(Comparator.comparingInt(CpuDTO::getMtPref).reversed())
                .findFirst().get();
        //find a cooler of preferred brand
        var cooler = getCooler(MIN * coolerFactor, MAX * coolerFactor);
        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * motherboardFactor, MAX * motherboardFactor, cpu.getSocket());
        //find a ram
        var ram = getRam(MIN * ramFactor, MAX * ramFactor);
        //find a gpu
        var gpu = getGpu(MIN * gpuFactor, MAX * gpuFactor, preferredGpuBrand);
        //find a storage
        var storage = getStorage(MIN * storageFactor, MAX * storageFactor);
        //find a psu
        var psu= getPsu(MIN * psuFactor, MAX * psuFactor);
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * caseFactor, MAX * caseFactor);

        map.put("cpu", cpu);
        map.put("cooler", cooler);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("gpu",gpu);
        map.put("psu", psu);
        map.put("computerCase", case_);
        return map;
    }

    @GetMapping("/form")
    public List<Map<String, Object>> getComputers(@RequestParam("type") String type,
                                                  @RequestParam("cpu") String cpu,
                                                  @RequestParam("gpu") String gpu,
                                                  @RequestParam("price") Float price) {
        List<Map<String, Object>> lists = new ArrayList<>();
        final float priceVariation = price * VARIATION;
        float currentPrice = price - priceVariation;
        for(int i = 0 ; i < 3; ++i, currentPrice += priceVariation) {
            try {
                switch (type) {
                    case OFFICE_WORKLOAD:
                        getOfficeComputer(currentPrice, cpu);
                        break;
                    case GAMING_WORKLOAD:
                        getGamingComputer(currentPrice, cpu, gpu);
                        break;
                    case PHOTO_WORKLOAD:
                        getPhotoComputer(currentPrice, cpu, gpu);
                        break;
                    case VIDEO_WORKLOAD:
                        getVideoComputer(currentPrice, cpu, gpu);
                        break;
                    case RENDERING_WORKLOAD:
                        getRenderingComputer(currentPrice, cpu, gpu);
                        break;
                }
                lists.add(getGamingComputer(currentPrice, cpu, gpu));
            } catch (NoSuchElementException e) {
                System.out.println(e);
            }
        }
        //TODO: add total price

        //lists.add(temp(type, cpu, gpu, price + price*0.6f));
        //lists.add(temp(type, cpu, gpu, price));
        //lists.add(temp(type, cpu, gpu, price - price*0.6f));
        return lists;
    }
}