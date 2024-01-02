package com.Student.keycloakclientcontroller;


import com.Student.dto.Policy;
import com.Student.security.KeycloakSecurityUtil;
import com.Student.security.SecurityConstants;
import com.Student.service.PolicyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.authorization.DecisionStrategy;
import org.keycloak.representations.idm.authorization.PolicyRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
public class PolicyController {
    @Autowired
    KeycloakSecurityUtil keycloakUtil;
    @Autowired
    PolicyService policyService;

    @Value("${realm}")
    private String realm;
    @Value("${springdoc.swagger-ui.oauth.client-id}")
    private String clientId;

    @GetMapping(value = "/policies")
    public List<Policy> getResources() {
        try {
            Keycloak keycloak = keycloakUtil.getKeycloakInstance();
            ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);
            List<PolicyRepresentation> policyRepresentations =
                    keycloak.realm(realm).clients().get(client.getId()).authorization().policies().policies();
            return policyService.mapPolicies(policyRepresentations);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    @PostMapping(value = "/policy")
    public Response createRole(Policy policy) {
        PolicyRepresentation policyRep = policyService.mapPolicyRep(policy);
        policyRep.setType(SecurityConstants.ROLE);
        policyRep.setDecisionStrategy(DecisionStrategy.UNANIMOUS);
        Map<String,String> configMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder("[");
        policy.getRoles().forEach(role->{
        stringBuilder.append("{\"id\":\""+role+"\",\"required\":false},");
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        configMap.put("roles",stringBuilder.toString());
        policyRep.setConfig(configMap);
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);
       Response response = keycloak.realm(realm).clients().get(client.getId()).authorization().policies().create(policyRep);
//        return Response.ok(policy).build();
          return response;
    }
}

