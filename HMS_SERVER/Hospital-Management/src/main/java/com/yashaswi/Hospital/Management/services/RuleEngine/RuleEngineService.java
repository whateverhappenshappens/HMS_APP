package com.yashaswi.Hospital.Management.services.RuleEngine;

import com.yashaswi.Hospital.Management.models.entities.Booking;
import com.yashaswi.Hospital.Management.models.entities.RulesDb;
import com.yashaswi.Hospital.Management.models.responses.Rule;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RuleEngineService {
    boolean validateBooking(Long userId, List<Booking> userBookingList, List<Booking>doctorBookingList);

    Rule createNewRule(RulesDb rulesDb);
}
