package com.yashaswi.Hospital.Management.services.KnowlegeBase;

import com.yashaswi.Hospital.Management.dao.RulesDao;
import com.yashaswi.Hospital.Management.models.entities.RulesDb;
import com.yashaswi.Hospital.Management.models.enums.RuleNamespace;
import com.yashaswi.Hospital.Management.models.responses.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class KnowledgeBaseServiceImpl implements KnowledgeBaseService{
    private RulesDao rulesDao;
    @Autowired
    public KnowledgeBaseServiceImpl (RulesDao rulesDao){
        this.rulesDao=rulesDao;
    }


    @Override
    public List<Rule> getAllRules() {
        List<RulesDb> rulesDbList=rulesDao.findAll();
       return  rulesDbList.stream()
                .map(rulesDb -> Rule.builder()
                        .ruleNamespace(rulesDb.getRuleNamespace())
                        .action(rulesDb.getAction())
                        .description(rulesDb.getDescription())
                        .condition(rulesDb.getCondition())
                        .build()
                ).toList();
    }

    @Override
    public List<Rule> getAllRuleByNamespace(String ruleNamespace) {
        List<RulesDb> rulesDbList=rulesDao.findByRuleNamespace(ruleNamespace);

         return  rulesDbList.stream()
                .map(rulesDb -> Rule.builder()
                        .ruleNamespace(rulesDb.getRuleNamespace())
                        .action(rulesDb.getAction())
                        .description(rulesDb.getDescription())
                        .condition(rulesDb.getCondition())
                        .build()
                ).toList();
    }

}
