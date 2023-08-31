package com.yashaswi.Hospital.Management.models.responses;

import com.yashaswi.Hospital.Management.models.enums.RuleNamespace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    RuleNamespace ruleNamespace;
    String condition;
    String action;
    String description;
}
