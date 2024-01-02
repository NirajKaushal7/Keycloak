package com.Student.keycloakclientcontroller;

import com.Student.dto.Resource;
import com.Student.security.KeycloakSecurityUtil;
import com.Student.service.ResourceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    private KeycloakSecurityUtil keycloakUtil;
    private ResourceService resourceService;
    @Autowired
    public ResourceController(KeycloakSecurityUtil keycloakUtil, ResourceService resourceService){
        this.keycloakUtil = keycloakUtil;
        this.resourceService = resourceService;
    }
    @Value("${realm}")
    private String realm;
    @Value("${springdoc.swagger-ui.oauth.client-id}")
    private String clientId;


    @GetMapping(value = "/resources")
    public Response getResources() {
        try {
            Keycloak keycloak = keycloakUtil.getKeycloakInstance();
            ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);
            List<ResourceRepresentation> resourceRepresentations =
                    keycloak.realm(realm).clients().get(client.getId()).authorization().resources().resources();
            List<Resource> resources = resourceService.mapResources(resourceRepresentations);
            return Response.ok(resources).build();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw e; // Rethrow the exception to propagate it to the client
        }
    }

    @PostMapping(value = "/resource")
    public Response createRole(@RequestBody  Resource resource) {
        ResourceRepresentation resourceRep = resourceService.mapResourceRep(resource);
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        Set<String> scopes = resource.getScopes();
        Set<ScopeRepresentation> scopeRepresentations = new HashSet<>();
        scopes.forEach(scope -> scopeRepresentations.add(new ScopeRepresentation(scope)));
        resourceRep.setScopes(scopeRepresentations);
        logger.info("Client Id : {}",clientId);
        ClientRepresentation client = keycloak.realm(realm).clients().findByClientId(clientId).get(0);
        keycloak.realm(realm).clients().get(client.getId()).authorization().resources().create(resourceRep);
        return Response.ok(resource).build();
    }
}
