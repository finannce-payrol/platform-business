package com.maldeniya.hodings.platformsystem.personal_sales.services;

import com.maldeniya.hodings.platformsystem.personal_sales.repositories.PersonalScalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class PersonalScaleService {

    private final PersonalScalesRepository personalScalesRepository;
    public Double getPersonalScaleQuota(String userId){
        return personalScalesRepository.getPersonalSalesByQuery(userId,
                YearMonth.from(Instant.now())
                        .atDay(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant(),
                YearMonth
                        .from(Instant.now())
                        .atEndOfMonth()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()).stream().map(personalSales ->
                personalSales.getScale() * personalSales.getPercentage() /100).reduce((val1, val2)->
                val1+val2).orElseGet(()-> (double) 0L);

    }
}
