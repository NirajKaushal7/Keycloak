package com.Student.dto;

import org.keycloak.representations.idm.RoleRepresentation.Composites;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Role {

    private String id;

    private String name;
    protected Set<String> realm;
    protected Map<String, List<String>> client;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRealm() {
        return this.realm;
    }

    public void setRealm(Set<String> realm) {
        this.realm = realm;
    }

    public Map<String, List<String>> getClient() {
        return this.client;
    }

    public void setClient(Map<String, List<String>> client) {
        this.client = client;
    }

}

