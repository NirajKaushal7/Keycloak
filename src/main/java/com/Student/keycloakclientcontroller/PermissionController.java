package com.Student.keycloakclientcontroller;

import com.Student.dto.Permission;

import com.Student.security.KeycloakSecurityUtil;
import com.Student.security.SecurityConstants;
import com.Student.service.PermissionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.authorization.DecisionStrategy;
import org.keycloak.representations.idm.authorization.Logic;
import org.keycloak.representations.idm.authorization.ResourcePermissionRepresentation;
import org.keycloak.representations.idm.authorization.ScopePermissionRepresentation;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
public class PermissionController {

    private KeycloakSecurityUtil keycloakUtil;
    private  PermissionService permissionService;
    @Autowired
    public PermissionController(KeycloakSecurityUtil keycloakUtil, PermissionService permissionService){
        this.keycloakUtil = keycloakUtil;
        this.permissionService = permissionService;
    }


    @Value("${realm}")
    private String realm;
    @Value("${springdoc.swagger-ui.oauth.client-id}")
    private String clientId;
    @PostMapping("/resource-permission")
    public Response createResourcePermission(@RequestBody Permission permission) {
        try {
            ResourcePermissionRepresentation resourcePermissionRep = permissionService.mapResourcePermissionRep(permission);
            resourcePermissionRep.setDecisionStrategy(DecisionStrategy.UNANIMOUS);
            resourcePermissionRep.setType(SecurityConstants.SCOPE);
            resourcePermissionRep.setLogic(Logic.POSITIVE);
            Set<String>  resources = new HashSet<>();
            resources.add("Test PUT Resource");
            Set<String>  policies = new HashSet<>();
            policies.add("Test Policy");
            resourcePermissionRep.setResources(resources);
            resourcePermissionRep.setPolicies(policies);
            Keycloak keycloak = keycloakUtil.getKeycloakInstance();
            ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);
            return keycloak.realm(realm).clients().get(client.getId()).authorization().permissions()
                    .resource().create(resourcePermissionRep);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating resource permission").build();
        }
    }

    @PostMapping("/scope-permission")
    public Response createScopePermission(@RequestBody Permission permission) {
        try {
            ScopePermissionRepresentation scopePermissionRep = permissionService.mapScopePermissionRep(permission);
            scopePermissionRep.setDecisionStrategy(DecisionStrategy.UNANIMOUS);
            scopePermissionRep.setType(SecurityConstants.SCOPE);
            scopePermissionRep.setLogic(Logic.POSITIVE);
            Set<String>  resources = permission.getResources();
            Set<String>  scopes = permission.getScopes();
            Set<String>  policies = permission.getPolicies();
            scopePermissionRep.setResources(resources);
            scopePermissionRep.setPolicies(policies);
            scopePermissionRep.setScopes(scopes);
            Keycloak keycloak = keycloakUtil.getKeycloakInstance();
            ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);
            return keycloak.realm(realm).clients().get(client.getId()).authorization().permissions()
                    .scope().create(scopePermissionRep);
           } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating resource permission").build();
        }
    }
}
