package com.yashaswi.Hospital.Management.controllers;

import com.yashaswi.Hospital.Management.models.entities.RulesDb;
import com.yashaswi.Hospital.Management.models.responses.Rule;
import com.yashaswi.Hospital.Management.services.RuleEngine.RuleEngineService;
import com.yashaswi.Hospital.Management.services.authentication.Authenticated;
import com.yashaswi.Hospital.Management.services.authorization.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hms/")
public class RuleController {

    RuleEngineService ruleEngineServiceImpl;
    @Autowired
    public RuleController(RuleEngineService ruleEngineServiceImpl){
        this.ruleEngineServiceImpl=ruleEngineServiceImpl;
    }


    @Authenticated
    @Authorized(allowedRoles = {"ADMIN"})
    @PostMapping("create-new-rule")
    ResponseEntity<Rule> createNewRule(@RequestBody RulesDb rulesDb){
        try{
            return new ResponseEntity<>(ruleEngineServiceImpl.createNewRule(rulesDb), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
