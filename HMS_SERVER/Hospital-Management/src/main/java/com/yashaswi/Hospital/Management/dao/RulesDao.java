package com.yashaswi.Hospital.Management.dao;

import com.yashaswi.Hospital.Management.models.entities.RulesDb;
import com.yashaswi.Hospital.Management.models.responses.Rule;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RulesDao extends JpaRepository<RulesDb, String> {
    List<RulesDb> findByRuleNamespace(String ruleNamespace);
    @NotNull List<RulesDb> findAll();
}
