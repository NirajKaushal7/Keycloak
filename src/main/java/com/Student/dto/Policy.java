package com.Student.dto;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.authorization.Logic;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class Policy {
    private String id;
    private String name;
    private String description;
//    private String type;
//    private Logic logic;
    private Set<String> roles;
}
