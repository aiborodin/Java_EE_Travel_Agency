package com.travelagency.entity.constraints;

import com.travelagency.entity.Agent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ActiveAgentValidator implements ConstraintValidator<ActiveAgent, Agent> {
    @Override
    public void initialize(ActiveAgent constraintAnnotation) { }

    @Override
    public boolean isValid(Agent agent, ConstraintValidatorContext context) {
        return agent.isActive();
    }
}
