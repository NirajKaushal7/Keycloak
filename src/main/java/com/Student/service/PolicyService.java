package com.Student.service;

import com.Student.dto.Policy;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.authorization.PolicyRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyService {
    public  List<Policy> mapPolicies(List<PolicyRepresentation> policyRepresentations) {
        List<Policy> policies = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(policyRepresentations)) {
            policyRepresentations.forEach(policyRep -> policies.add(mapPolicy(policyRep)));
        }
        return policies;
    }

    public Policy mapPolicy(PolicyRepresentation policyRep) {
        Policy policy = new Policy();
        policy.setId(policyRep.getId());
        policy.setName(policyRep.getName());
        policy.setDescription(policyRep.getDescription());
        return policy;
    }
    public PolicyRepresentation mapPolicyRep(Policy policy) {
        PolicyRepresentation policyRep = new PolicyRepresentation();
        policyRep.setId(policy.getId());
        policyRep.setName(policy.getName());
        policyRep.setDescription(policy.getDescription());
        return policyRep;
    }
}
