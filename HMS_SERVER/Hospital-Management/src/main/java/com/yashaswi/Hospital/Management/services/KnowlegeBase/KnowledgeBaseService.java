package com.yashaswi.Hospital.Management.services.KnowlegeBase;

import com.yashaswi.Hospital.Management.models.responses.Rule;
import org.springframework.stereotype.Service;

import java.util.List;
public interface KnowledgeBaseService {
    List<Rule> getAllRules();
    List<Rule> getAllRuleByNamespace(String ruleNamespace);
}
