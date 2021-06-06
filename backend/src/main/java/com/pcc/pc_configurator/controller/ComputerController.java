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
import java.util.function.Predicate;
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
                        && (p.getProduct().getPrice() <= maxPrice)
                && (p.getModulesCount() >= 2))
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
        final PriceFactors factors = new PriceFactors(OFFICE_WORKLOAD);
        //find a cpu with integrated graphics of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> p.isIntegratedGPU()
                        && (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * factors.getCpuFactor()))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * factors.getCpuFactor()))//brand
                )
                .sorted(Comparator.comparingInt(CpuDTO::getStPref).reversed())
                .findFirst().get();
        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * factors.getMotherboardFactor(), MAX * factors.getMotherboardFactor(), cpu.getSocket());
        //find a ram
        var ram = ramDtoList.stream()
                .filter(p -> (p.getProduct().getPrice() >= MIN * factors.getRamFactor())//price MIN
                        && (p.getProduct().getPrice() <= MAX * factors.getRamFactor()))
                .sorted(Comparator.comparingInt(RamDTO::getSpeed))
                .findFirst()
                .get();
        //find a storage
        var storage = getStorage(MIN * factors.getStorageFactor(), MAX * factors.getStorageFactor());
        //find a psu
        var psu = getPsu(MIN * factors.getPsuFactor(), MAX * factors.getPsuFactor());
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * factors.getCaseFactor(), MAX * factors.getCaseFactor());

        map.put("cpu", cpu);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("psu", psu);
        map.put("computerCase", case_);
        map.put("totalPrice", (
                cpu.getProduct().getPrice()
                + motherboard.getProduct().getPrice()
                + ram.getProduct().getPrice()
                + storage.getProduct().getPrice()
                + psu.getProduct().getPrice()
                + case_.getProduct().getPrice()));
        return map;
    }

    private class PriceFactors {
        private float[] factors = new float[] {0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f};

        public PriceFactors(String workload) {
            switch (workload) {
                case OFFICE_WORKLOAD:
                    factors = new float[]{0.3f, 0.f, 0.167f, 0.16f, 0.13f, 0.f, 0.113f, 0.13f};
                    break;
                case GAMING_WORKLOAD:
                    factors = new float[]{0.21f, 0.04f, 0.11f, 0.08f, 0.1f, 0.33f, 0.06f, 0.07f};
                    break;
                case PHOTO_WORKLOAD:
                    factors = new float[]{0.24f, 0.06f, 0.13f, 0.13f, 0.12f, 0.17f, 0.08f, 0.07f};
                    break;
                case VIDEO_WORKLOAD:
                    factors = new float[]{0.27f, 0.04f, 0.1f, 0.1f, 0.14f, 0.19f, 0.08f, 0.08f};
                    break;
                case RENDERING_WORKLOAD:
                    factors = new float[]{0.27f, 0.039f, 0.067f, 0.12f, 0.17f, 0.235f, 0.059f, 0.04f};
                    break;
            }
        }

        float getCpuFactor() { return factors[0]; }
        float getCoolerFactor() { return factors[1]; }
        float getMotherboardFactor() { return factors[2]; }
        float getRamFactor() { return factors[3]; }
        float getStorageFactor() { return factors[4]; }
        float getGpuFactor() { return factors[5]; }
        float getPsuFactor() { return factors[6]; }
        float getCaseFactor() { return factors[7]; }
    }

    private Map<String, Object> getComputer(Float budget, String preferredCpuBrand, String preferredGpuBrand, String workload) {
        Map<String, Object> map = new HashMap<>();
        //factors
        final float MIN = budget - (budget * VARIATION);
        final float MAX = budget + (budget * VARIATION);
        PriceFactors factors = new PriceFactors(workload);

        Comparator<CpuDTO> sortComparator = (workload.equals(VIDEO_WORKLOAD) || workload.equals(RENDERING_WORKLOAD))
                ? (Comparator.comparingInt(CpuDTO::getMtPref).reversed())
                : (Comparator.comparingInt(CpuDTO::getStPref).reversed());

        //find a cpu of preferred brand
        var cpu = cpuDtoList.stream()
                .filter(p -> (preferredCpuBrand.equals("any") ? true : p.getProduct().getBrand().equals(preferredCpuBrand))
                        && (p.getProduct().getPrice() >= (MIN * factors.getCpuFactor()))//price MIN
                        && (p.getProduct().getPrice() <= (MAX * factors.getCpuFactor())) //brand
                )
                .sorted(sortComparator)
                .findFirst().get();
        //find a cooler of preferred brand
        var cooler = getCooler(MIN * factors.getCoolerFactor(), MAX * factors.getCoolerFactor());
        //find a motherboard that matches the socket
        var motherboard = getMotherboard(MIN * factors.getMotherboardFactor(), MAX * factors.getMotherboardFactor(), cpu.getSocket());
        //find a ram
        var ram = getRam(MIN * factors.getRamFactor(), MAX * factors.getRamFactor());
        //find a gpu
        var gpu = getGpu(MIN * factors.getGpuFactor(), MAX * factors.getGpuFactor(), preferredGpuBrand);
        //find a storage
        var storage = getStorage(MIN * factors.getStorageFactor(), MAX * factors.getStorageFactor());
        //find a psu
        var psu= getPsu(MIN * factors.getPsuFactor(), MAX * factors.getPsuFactor());
        //find a case, match form factor of motherboard
        var case_ = getCase(MIN * factors.getCaseFactor(), MAX * factors.getCaseFactor());

        map.put("cpu", cpu);
        map.put("cooler", cooler);
        map.put("motherboard", motherboard);
        map.put("ram", ram);
        map.put("storage", storage);
        map.put("gpu",gpu);
        map.put("psu", psu);
        map.put("computerCase", case_);
        map.put("totalPrice", (cpu.getProduct().getPrice()
                + cooler.getProduct().getPrice()
                + motherboard.getProduct().getPrice()
                + ram.getProduct().getPrice()
                + storage.getProduct().getPrice()
                + gpu.getProduct().getPrice()
                + psu.getProduct().getPrice()
                + case_.getProduct().getPrice()));
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
        final boolean isOffice = type.equals(OFFICE_WORKLOAD);

        for(int i = 0 ; i < 3; ++i, currentPrice += priceVariation) {
            try {
                if (isOffice) {
                    lists.add(getOfficeComputer(currentPrice, cpu));
                } else {
                    lists.add(getComputer(currentPrice, cpu, gpu, type));
                }
                lists.get(lists.size() - 1).put("priceOption", i);
            } catch (NoSuchElementException e) {
                System.out.println(e);
            }
        }
        //TODO: add total price

        return lists;
    }
}