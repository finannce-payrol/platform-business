package com.maldeniya.hodings.platformsystem.personal_sales.controllers;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import com.maldeniya.hodings.platformsystem.personal_sales.services.PersonalScaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/{userId}/scales")
@AllArgsConstructor
public class PersonalScalesController {
    private final PersonalScaleService personalScaleService;

    @PostMapping
    public ResponseEntity addPersonalScale(@PathVariable String userId, @RequestBody PersonalSales personalSales){
        return ResponseEntity.ok(personalScaleService.addPersonalScale(userId, personalSales));
    }
    @GetMapping(value = "/{scaleId}")
    public ResponseEntity getPersonalScales(@PathVariable String userId, @PathVariable String scaleId){
        return ResponseEntity.ok(personalScaleService.getPersonalScales(userId, scaleId));
    }

    @GetMapping
    public ResponseEntity<Double> getPersonalScaleQuota(@PathVariable String userId){
        return ResponseEntity.ok(personalScaleService.getPersonalScaleQuota(userId));
    }
}
