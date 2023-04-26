package com.maldeniya.hodings.platformsystem.personal_sales.services;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import com.maldeniya.hodings.platformsystem.personal_sales.repositories.PersonalScalesRepository;
import com.maldeniya.hodings.platformsystem.users.entity.User;
import com.maldeniya.hodings.platformsystem.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.*;

@Service
@AllArgsConstructor
public class PersonalScaleService {

    private final PersonalScalesRepository personalScalesRepository;
    private final UserRepository userRepository;
    public Double getPersonalScaleQuota(String userId){
        return personalScalesRepository.getPersonalSalesByQuery(
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

    public PersonalSales getPersonalScales(String userId, String scaleId){
        return personalScalesRepository.findByIdAndUserId(scaleId, userId).orElseThrow(()-> new RuntimeException("Scale not found"));
    }

    public PersonalSales addPersonalScale(String userId, PersonalSales personalSales){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return personalScalesRepository.save(personalSales);
    }
}
