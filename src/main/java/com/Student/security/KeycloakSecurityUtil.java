package com.Student.security;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    Keycloak keycloak;

    @Value("${server-url}")
    private String serverUrl;

    @Value("${realm}")
    private String realm;

    @Value("${client-id}")
    private String clientId;
    @Value("${springdoc.swagger-ui.oauth.client-secret}")
    private String clientSecret;
    @Value("${grant-type}")
    private String grantType;

    @Value("${name}")
    private String username;

    @Value("${password}")
    private String password;

    public Keycloak getKeycloakInstance() {
        if(keycloak == null) {

//            keycloak = KeycloakBuilder.builder()
//                        .serverUrl("http://localhost:8080")
//                        .realm("master")
//                        .username("admin")
//                        .password("admin")
//                        .clientId("admin-cli")
//                        .build();

            keycloak = KeycloakBuilder
                    .builder().serverUrl(serverUrl).realm(realm)
                    .clientId(clientId).grantType(OAuth2Constants.PASSWORD)
                    .username(username).password(password).build();

//		// Client "idm-client" needs service-account with at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
//		Keycloak keycloak = KeycloakBuilder.builder() //
//				.serverUrl(serverUrl) //
//				.realm(realm) //
//				.grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
//				.clientId(clientId) //
//				.clientSecret(clientSecret).build();

            // User "idm-admin" needs at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
//            keycloak = KeycloakBuilder.builder() //
//                    .serverUrl(serverUrl) //
//                    .realm(realm) //
//                    .grantType(OAuth2Constants.PASSWORD) //
//                    .clientId(clientId) //
//                    .clientSecret(clientSecret) //
//                    .username(username) //
//                    .password(password) //
//                    .build();
        }
        return keycloak;
    }

}
