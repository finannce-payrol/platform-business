package com.maldeniya.hodings.platformsystem.personal_sales.controllers;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import com.maldeniya.hodings.platformsystem.personal_sales.services.PersonalSaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/{userId}/sales")
@AllArgsConstructor
public class PersonalSalesController {
    private final PersonalSaleService personalSaleService;

    @PostMapping
    public ResponseEntity addPersonalSale(@PathVariable String userId, @RequestBody PersonalSales personalSales){
        return ResponseEntity.ok(personalSaleService.addPersonalSale(userId, personalSales));
    }
    @GetMapping(value = "/{saleId}")
    public ResponseEntity getPersonalSales(@PathVariable String userId, @PathVariable String saleId){
        return ResponseEntity.ok(personalSaleService.getPersonalSales(userId, saleId));
    }

    @GetMapping
    public ResponseEntity<Double> getPersonalSaleQuota(@PathVariable String userId){
        return ResponseEntity.ok(personalSaleService.getPersonalSaleQuota(userId));
    }
}
