package com.maldeniya.hodings.platformsystem.personal_sales.services;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import com.maldeniya.hodings.platformsystem.personal_sales.repositories.PersonalSalesRepository;
import com.maldeniya.hodings.platformsystem.users.entity.User;
import com.maldeniya.hodings.platformsystem.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
@AllArgsConstructor
public class PersonalSaleService {

    private final PersonalSalesRepository personalSalesRepository;
    private final UserRepository userRepository;
    public Double getPersonalSaleQuota(String userId){
        return personalSalesRepository.getPersonalSalesByQuery(
                        userId,
                        getEpochValue(DayType.START),
                        getEpochValue(DayType.END)).stream()
                .map(personalSales ->
                        personalSales.getScale() * personalSales.getPercentage() /100)
                .reduce((val1, val2)->
                        val1+val2)
                .orElseGet(()-> (double) 0L);

    }

    private long getEpochValue(DayType dayType){
        LocalDate day = LocalDate.of(ZonedDateTime.now().getYear(), ZonedDateTime.now().getMonthValue(), 1);
        Instant instant;
        if(dayType.equals(DayType.END)){
            day = day.plusMonths(1).minusDays(1);
            instant = day.atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC);
        }else {
            instant = day.atTime(LocalTime.MIN).toInstant(ZoneOffset.UTC);
        }
        return instant.toEpochMilli();
    }

    public PersonalSales getPersonalSales(String userId, String scaleId){
        return personalSalesRepository.findByIdAndUserId(scaleId, userId).orElseThrow(()-> new RuntimeException("Scale not found"));
    }

    public PersonalSales addPersonalSale(String userId, PersonalSales personalSales){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return personalSalesRepository.save(personalSales);
    }
}
