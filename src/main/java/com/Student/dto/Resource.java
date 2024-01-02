package com.Student.dto;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;

import java.util.Set;

@Getter
@Setter
public class Resource {
    private String id;
    private String name;
    private Set<String> uris;
    private String type;
    private Set<String> scopes;
    private String displayName;

}
