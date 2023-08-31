package com.yashaswi.Hospital.Management.models.entities;

import com.yashaswi.Hospital.Management.models.enums.RuleNamespace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RulesDb {
    @Id
    @Column(name = "rule_id")
    @GeneratedValue
    private Long ruleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "rule_namespace")
    private RuleNamespace ruleNamespace;

    @Column(name = "condition")
    private String condition;
    @Column(name = "action")
    private String action;
    @Column(name = "description")
    private String description;
    @Data
    static class IdClass implements Serializable {
        private String ruleNamespace;
        private String ruleId;
    }

}
