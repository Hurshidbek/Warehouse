package uz.oak.warehouse.service;

import uz.oak.warehouse.entity.TempHouse;
import uz.oak.warehouse.entity.Warehouse;
import uz.oak.warehouse.exception.NotFoundException;
import uz.oak.warehouse.payload.ProductMaterialDto;
import uz.oak.warehouse.payload.ProductRequestDto;
import uz.oak.warehouse.payload.ProductResponseDto;
import uz.oak.warehouse.payload.Result;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import uz.oak.warehouse.entity.Product;
import uz.oak.warehouse.entity.ProductMaterial;
import uz.oak.warehouse.repository.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class WarehouseService {

    private final ProductRepository productRepository;
    private final ProductMaterialRepository productMaterialRepository;
    private final WarehouseRepository warehouseRepository;
    private final TempHouseRepository tempHouseRepository;


    public Result getAllProductsInfo(List<ProductRequestDto> requestDtoList) throws Exception {

        tempHouseRepository.deleteAll();
        saveAllFromWareHouseToTempHouse();

        List<ProductResponseDto> productResponseDto = new ArrayList<>();

        for (ProductRequestDto requestDto : requestDtoList) {
            if (getProductByName(requestDto.getName()) == null) {
                throw new NotFoundException(requestDto.getName() + " product not found", Product.class, "name");
            }
            productResponseDto.add(getAllProductMaterials(requestDto));
        }

        return new Result(productResponseDto);
    }


    private void saveAllFromWareHouseToTempHouse() {
        tempHouseRepository.saveAll(getAllFromWareHouse());
    }


    private List<TempHouse> getAllFromWareHouse() {
        List<TempHouse> tempHouseList = new ArrayList<>();

        List<Warehouse> warehouseList = warehouseRepository.findAll();

        for (Warehouse warehouse : warehouseList) {
            TempHouse tempHouse = new TempHouse();
            tempHouse.setWarehouseId(warehouse.getId());

            tempHouse.setMaterialName(warehouse.getMaterial().getName());

            tempHouse.setQty(warehouse.getRemainder());
            tempHouse.setPrice(warehouse.getPrice());

            tempHouseList.add(tempHouse);
        }
        return tempHouseList;
    }


    private Product getProductByName(String name) throws Exception {
        return productRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException("There is no product regards to given name", Product.class, "name"));
    }


    private ProductResponseDto getAllProductMaterials(ProductRequestDto requestDto) throws Exception {
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setProduct_name(requestDto.getName());
        productResponseDto.setProductQty(requestDto.getQuantity());

        List<TempHouse> productsInTempHouse = new ArrayList<>();

        for (ProductMaterialDto productMaterialDto : getProductMaterials(requestDto)) {
            productsInTempHouse.addAll(getMaterials(productMaterialDto));
        }

        productResponseDto.setProduct_materials(productsInTempHouse);

        return productResponseDto;
    }


    private List<ProductMaterialDto> getProductMaterials(ProductRequestDto requestDto) throws Exception {

        Product product = getProductByName(requestDto.getName());

        List<ProductMaterial> productMaterialsByProductId =
                productMaterialRepository.findAllByProductId(product.getId());

        List<ProductMaterialDto> productMaterialDtos = new ArrayList<>();

        for (ProductMaterial productMaterial : productMaterialsByProductId) {
            ProductMaterialDto productMaterialDto = new ProductMaterialDto();
            productMaterialDto.setName(productMaterial.getMaterial().getName());
            productMaterialDto.setQuantity(productMaterial.getQuantity() * requestDto.getQuantity());
            productMaterialDtos.add(productMaterialDto);
        }
        return productMaterialDtos;
    }


    private List<TempHouse> getMaterials(ProductMaterialDto productMaterialDto) {
        List<TempHouse> tempHouseMaterialsList = getAllByMaterialsName(productMaterialDto.getName());
        List<TempHouse> tempHouses = new ArrayList<>();

        for (TempHouse tempHouse : tempHouseMaterialsList) {
            if (tempHouse.getQty() >= productMaterialDto.getQuantity()) {
                tempHouses.add(new TempHouse(
                        tempHouse.getWarehouseId(),
                        tempHouse.getMaterialName(),
                        productMaterialDto.getQuantity(),
                        tempHouse.getPrice()
                ));
                tempHouse.setQty(tempHouse.getQty() - productMaterialDto.getQuantity());
                tempHouseRepository.saveAll(tempHouseMaterialsList);
                return tempHouses;
            } else if (tempHouse.getQty() > 0) {
                tempHouses.add(new TempHouse(
                        tempHouse.getWarehouseId(),
                        tempHouse.getMaterialName(),
                        tempHouse.getQty(),
                        tempHouse.getPrice()
                ));
                productMaterialDto.setQuantity(productMaterialDto.getQuantity() - tempHouse.getQty());
                tempHouse.setQty(0);
            }
        }
        if (productMaterialDto.getQuantity() > 0) {
            tempHouses.add(new TempHouse(
                    null,
                    productMaterialDto.getName(),
                    productMaterialDto.getQuantity(),
                    0
            ));
        }

        tempHouseRepository.saveAll(tempHouseMaterialsList);
        return tempHouses;
    }


    public List<TempHouse> getAllByMaterialsName(String materialName) {
        return tempHouseRepository.findAllByMaterialName(materialName);
    }


}
