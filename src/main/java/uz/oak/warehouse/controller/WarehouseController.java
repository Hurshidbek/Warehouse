package uz.oak.warehouse.controller;

import uz.oak.warehouse.payload.ProductRequestDto;
import uz.oak.warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/warehouse")
public class WarehouseController {

    private final WarehouseService service ;


    @PostMapping("/get")
    public ResponseEntity<?> getProduct(
            @RequestBody List<ProductRequestDto> requestDtos
    ) throws Exception {
        return ResponseEntity.status(200).body(service.getAllProductsInfo(requestDtos));
    }



}
