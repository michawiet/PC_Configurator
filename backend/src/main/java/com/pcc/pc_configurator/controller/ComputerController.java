package com.pcc.pc_configurator.controller;

import com.pcc.pc_configurator.DTO.*;
import com.pcc.pc_configurator.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

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
    List<ProductsDTO> productsDtoList = new ArrayList<>();
    List<CaseDTO> caseDtoList = new ArrayList<>();
    List<CoolerDTO> coolerDtoList = new ArrayList<>();
    List<CpuDTO> cpuDtoList = new ArrayList<>();
    List<GpuDTO> gpuDtoList = new ArrayList<>();
    List<MotherboardDTO> motherboardDtoList = new ArrayList<>();
    List<PsuDTO> psuDtoList = new ArrayList<>();
    List<RamDTO> ramDtoList = new ArrayList<>();
    List<StorageDTO> storageDtoList = new ArrayList<>();
    Float[][] wspOffice = {
            {0.0f, 0.5f},   //cpu
            {0.0f, 0.5f},   //cooler
            {0.0f, 0.5f},   //motherboard
            {0.0f, 0.5f},   //ram
            {0.0f, 0.5f},   //storage
            {0.0f, 0.5f},   //gpu
            {0.0f, 0.5f},   //psu
            {0.0f, 0.5f}    //case
    };
    Float[][] wspGames = {
            {0.0f, 0.5f},   //cpu
            {0.0f, 0.5f},   //cooler
            {0.0f, 0.5f},   //motherboard
            {0.0f, 0.5f},   //ram
            {0.0f, 0.5f},   //storage
            {0.0f, 0.5f},   //gpu
            {0.0f, 0.5f},   //psu
            {0.0f, 0.5f}    //case
    };
    Float[][] wspPhotoEditing = {
            {0.0f, 0.5f},   //cpu
            {0.0f, 0.5f},   //cooler
            {0.0f, 0.5f},   //motherboard
            {0.0f, 0.5f},   //ram
            {0.0f, 0.5f},   //storage
            {0.0f, 0.5f},   //gpu
            {0.0f, 0.5f},   //psu
            {0.0f, 0.5f}    //case
    };
    Float[][] wspVideoEditing = {
            {0.0f, 0.5f},   //cpu
            {0.0f, 0.5f},   //cooler
            {0.0f, 0.5f},   //motherboard
            {0.0f, 0.5f},   //ram
            {0.0f, 0.5f},   //storage
            {0.0f, 0.5f},   //gpu
            {0.0f, 0.5f},   //psu
            {0.0f, 0.5f}    //case
    };
    Float[][] wsp3DRendering = {
            {0.0f, 0.5f},   //cpu
            {0.0f, 0.5f},   //cooler
            {0.0f, 0.5f},   //motherboard
            {0.0f, 0.5f},   //ram
            {0.0f, 0.5f},   //storage
            {0.0f, 0.5f},   //gpu
            {0.0f, 0.5f},   //psu
            {0.0f, 0.5f}    //case
    };

    @EventListener(ApplicationReadyEvent.class)
    //@RequestMapping
    public void fillLists() {
        for (var product : productRepository.findAll()) { productsDtoList.add(modelMapper.map(product, ProductsDTO.class)); }
        for (var case_ : caseRepository.findAll()) { caseDtoList.add(modelMapper.map(case_, CaseDTO.class)); }
        for (var cooler : coolerRepository.findAll()) { coolerDtoList.add(modelMapper.map(cooler, CoolerDTO.class)); }
        for (var cpu : cpuRepository.findAll()) { cpuDtoList.add(modelMapper.map(cpu, CpuDTO.class)); }
        for (var gpu : gpuRepository.findAll()) { gpuDtoList.add(modelMapper.map(gpu, GpuDTO.class)); }
        for (var motherboard : motherboardRepository.findAll()) { motherboardDtoList.add(modelMapper.map(motherboard, MotherboardDTO.class)); }
        for (var psu : psuRepository.findAll()) { psuDtoList.add(modelMapper.map(psu, PsuDTO.class)); }
        for (var ram : ramRepository.findAll()) { ramDtoList.add(modelMapper.map(ram, RamDTO.class)); }
        for (var storage : storageRepository.findAll()) { storageDtoList.add(modelMapper.map(storage, StorageDTO.class)); }

    }

    public Map<String, Object> temp(String type, String cpu, String gpu, Float price) {
        Map<String, Object> products = new HashMap<>();
        List<CpuDTO> subCpuList = new ArrayList<>();
        List<CoolerDTO> subCoolerList = new ArrayList<>();
        List<MotherboardDTO> subMotherboardList = new ArrayList<>();
        List<RamDTO> subRamList = new ArrayList<>();
        List<StorageDTO> subStorageList = new ArrayList<>();
        List<GpuDTO> subGpuList = new ArrayList<>();
        List<PsuDTO> subPsuList = new ArrayList<>();
        List<CaseDTO> subCaseList = new ArrayList<>();

        Map<String, Float[][]> map = new HashMap<>() {{
            put("office", wspOffice);
            put("games", wspGames);
            put("photoEditing", wspPhotoEditing);
            put("videoEditing", wspVideoEditing);
            put("3DRendering", wsp3DRendering);
        }};
        Random random = new Random();
        if (map.containsKey(type)) {
            Float[][] temporaryWsp = map.get(type);

            for (int i = 0; i < cpuDtoList.size(); ++i) {
                if (cpuDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[0][0] && cpuDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[0][1] && cpuDtoList.get(i).getProduct().getBrand().equals(cpu)) {
                    subCpuList.add(cpuDtoList.get(i));
                }
            }
            for (int i = 0; i < coolerDtoList.size(); ++i) {
                if (coolerDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[1][0] && coolerDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[1][1]) {
                    subCoolerList.add(coolerDtoList.get(i));
                }
            }
            products.putIfAbsent("cpu",subCpuList.get(random.nextInt(subCpuList.size()-1)));
            for (int i = 0; i < motherboardDtoList.size(); ++i) {
                if (motherboardDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[2][0] && motherboardDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[2][1]&& motherboardDtoList.get(i).getSocket().equals(((CpuDTO) products.get("cpu")).getSocket())) {
                    subMotherboardList.add(motherboardDtoList.get(i));
                }
            }
            for (int i = 0; i < ramDtoList.size(); ++i) {
                if (ramDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[3][0] && ramDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[3][1] ) {
                    subRamList.add(ramDtoList.get(i));
                }
            }
            for (int i = 0; i < storageDtoList.size(); ++i) {
                if (storageDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[4][0] && storageDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[4][1] ) {
                    subStorageList.add(storageDtoList.get(i));
                }
            }
            for (int i = 0; i < gpuDtoList.size(); ++i) {
                if (gpuDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[5][0] && gpuDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[5][1] && gpuDtoList.get(i).getChipset().substring(0,gpuDtoList.get(i).getChipset().indexOf(' ')).equals(gpu)// &&
                    /*!((CpuDTO) products.get("cpu")).isIntegratedGPU()*/) {
                    subGpuList.add(gpuDtoList.get(i));
                }
            }
            for (int i = 0; i < psuDtoList.size(); ++i) {
                if (psuDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[6][0] && psuDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[6][1] &&
                        (((CpuDTO) products.get("cpu")).getTdpW()) + 100 <= psuDtoList.get(i).getWattage()) {
                    subPsuList.add(psuDtoList.get(i));
                }
            }
            for (int i = 0; i < caseDtoList.size(); ++i) {
                if (caseDtoList.get(i).getProduct().getPrice() >= price * temporaryWsp[7][0] && caseDtoList.get(i).getProduct().getPrice() <= price * temporaryWsp[7][1] ) {
                    subCaseList.add(caseDtoList.get(i));
                }
            }
            products.putIfAbsent("cooler", subCoolerList.get(random.nextInt(subCoolerList.size()-1)));
            products.putIfAbsent("motherboard",subMotherboardList.get(random.nextInt(subMotherboardList.size()-1)));
            products.putIfAbsent("ram",subRamList.get(random.nextInt(subRamList.size()-1)));
            products.putIfAbsent("storage",subStorageList.get(random.nextInt(subStorageList.size()-1)));
            products.putIfAbsent("gpu", subGpuList.get(random.nextInt(subGpuList.size()-1)));
            products.putIfAbsent("psu",subPsuList.get(random.nextInt(subPsuList.size()-1)));
            products.putIfAbsent("case",subCaseList.get(random.nextInt(subCaseList.size()-1)));
        }
        var temp1 = ((CpuDTO) products.get("cpu")).getProduct().getPrice() +((CoolerDTO) products.get("cooler")).getProduct().getPrice() +
                ((MotherboardDTO) products.get("motherboard")).getProduct().getPrice() +((RamDTO) products.get("ram")).getProduct().getPrice() +
                ((StorageDTO) products.get("storage")).getProduct().getPrice() +((GpuDTO) products.get("gpu")).getProduct().getPrice() +
                ((PsuDTO) products.get("psu")).getProduct().getPrice() +((CaseDTO) products.get("case")).getProduct().getPrice();
        System.out.println(Math.round((temp1)*100.f)/100.f);

        return products;
    }

    @GetMapping("/form")
    public List<Map<String, Object>> getComputers(@RequestParam("type") String type,
                                                  @RequestParam("cpu") String cpu,
                                                  @RequestParam("gpu") String gpu,
                                                  @RequestParam("price") Float price) {
        List<Map<String, Object>> lists = new ArrayList<>();

        lists.add(temp(type, cpu, gpu, price + price*0.6f));
        lists.add(temp(type, cpu, gpu, price));
        lists.add(temp(type, cpu, gpu, price - price*0.6f));
        return lists;
    }
}