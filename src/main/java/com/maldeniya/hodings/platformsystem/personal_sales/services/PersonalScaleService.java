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

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class PersonalScaleService {

    private final PersonalScalesRepository personalScalesRepository;
    private final UserRepository userRepository;
    public Double getPersonalScaleQuota(String userId){

        LocalDate todayDate = LocalDate.now();
        long start = todayDate.withDayOfMonth(1).toEpochDay();
        long end = todayDate.withDayOfMonth(30).toEpochDay();
        return personalScalesRepository.getPersonalSalesByQuery(userId,
                start,
                end).stream().map(personalSales ->
                personalSales.getScale() * personalSales.getPercentage() /100).reduce((val1, val2)->
                val1+val2).orElseGet(()-> (double) 0L);

    }

    public PersonalSales getPersonalScales(String userId, String scaleId){
        return personalScalesRepository.findByIdAndUserId(scaleId, userId).orElseThrow(()-> new RuntimeException("Scale not found"));
    }

    public PersonalSales addPersonalScale(String userId, PersonalSales personalSales){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return personalScalesRepository.save(personalSales);
    }
}
