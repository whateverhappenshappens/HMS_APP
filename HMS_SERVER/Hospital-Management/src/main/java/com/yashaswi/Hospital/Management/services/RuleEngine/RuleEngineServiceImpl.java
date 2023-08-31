package com.yashaswi.Hospital.Management.services.RuleEngine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yashaswi.Hospital.Management.dao.RulesDao;
import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.entities.RulesDb;
import com.yashaswi.Hospital.Management.models.enums.RuleNamespace;
import com.yashaswi.Hospital.Management.models.responses.Rule;
import com.yashaswi.Hospital.Management.services.booking.BookingService;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RuleEngineServiceImpl implements RuleEngineService {

    RulesDao rulesDao;

    @Autowired
    public RuleEngineServiceImpl(RulesDao rulesDao) {
        this.rulesDao = rulesDao;
    }

    @Override
    public boolean validateBooking(Long userId, List<Booking> userBookingList, List<Booking>doctorBookingList) {
        List<RulesDb> rulesDbList=rulesDao.findAll();
        Gson gson = new Gson();
        List<Map> conditions = rulesDbList.stream().map(rulesDb -> {
            return gson.fromJson(rulesDb.getCondition(), Map.class);
        }).toList();
        Map<String,Integer> bookingLimits = (Map<String, Integer>) conditions.get(0).get("BOOKING");
        boolean isValid = validateUserBookingLimit(userId, userBookingList, bookingLimits);
//                && validateDoctorBookingLimit(doctorId, doctorBookingList, bookingLimits)
//                && validateSameUserSameDoctorLimit(userId, doctorId, userBookingList, bookingLimits)
//                && validateMaxBookDuration(bookingDate, bookingLimits);





        return true;
    }

    private boolean validateUserBookingLimit(Long userId, List<Booking> userBookingList, Map<String, Integer> bookingLimits) {
        return true;
    }

    @Override
    public Rule createNewRule(RulesDb rulesDb) {
        Gson gson=new Gson();
        RulesDb rulesDb1 = RulesDb.builder()
                .ruleNamespace(rulesDb.getRuleNamespace())
                .action(rulesDb.getAction())
                .condition(rulesDb.getCondition())
                .description(rulesDb.getDescription())
                .build();
        rulesDao.save(rulesDb1);
        return Rule.builder()
                .ruleNamespace(rulesDb1.getRuleNamespace())
                .description(rulesDb1.getDescription())
                .action(rulesDb1.getAction())
                .condition(rulesDb1.getCondition())
                .build();
    }

}
