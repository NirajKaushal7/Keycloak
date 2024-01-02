package com.Student.keycloakclientcontroller;

import com.Student.dto.Client;
import com.Student.dto.Realm;
import com.Student.security.KeycloakSecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
public class RealmController {
    private KeycloakSecurityUtil keycloakUtil;
    @Autowired
    public RealmController(KeycloakSecurityUtil keycloakUtil)
    {
        this.keycloakUtil = keycloakUtil;
    }
    @Value("${realm}")
    private String realm;

    /*
    * To create Realm assign the roles
    * */
    @PostMapping("realm")
    public Response createRealm(Realm realm) {
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(realm.getName());
//        realmRepresentation.setDisplayName(realm.getName());
        keycloak.realms().create(realmRepresentation);
        return Response.ok(realm).build();
    }
    @PostMapping("client")
    public Response createClient(Client client) {
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(client.getClientId());
        clientRepresentation.setName(client.getName());
        clientRepresentation.setSecret(client.getSecret());
        clientRepresentation.setRedirectUris(client.getRedirectUris());
        clientRepresentation.setWebOrigins(client.getWebOrigins());
        clientRepresentation.setAuthorizationServicesEnabled(true);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setStandardFlowEnabled(true);
        clientRepresentation.setServiceAccountsEnabled(true);
        keycloak.realm(realm).clients().create(clientRepresentation);
        return Response.ok(client).build();
    }
}
