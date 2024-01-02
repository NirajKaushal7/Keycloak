package com.Student.service;

import com.Student.dto.Resource;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResourceService {
    public  List<Resource> mapResources(List<ResourceRepresentation> resourceRepresentations) {
        List<Resource> resources = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(resourceRepresentations)) {
            resourceRepresentations.forEach(resourceRep -> resources.add(mapResource(resourceRep)));
        }
        return resources;
    }


    public Resource mapResource(ResourceRepresentation resourceRep) {
        Resource resource = new Resource();
        resource.setId(resourceRep.getId());
        resource.setName(resourceRep.getName());
        resource.setUris(resourceRep.getUris());
        Set<ScopeRepresentation> scopeRepresentations = resourceRep.getScopes();
        Set<String> scopes = new HashSet<>();
        scopeRepresentations.forEach(scope -> scopes.add(scope.getName()));
        resource.setScopes(scopes);
        resource.setDisplayName(resourceRep.getDisplayName());
        resource.setType(resourceRep.getType());
        return resource;
    }
    public ResourceRepresentation mapResourceRep(Resource resource) {
        ResourceRepresentation resourceRep = new ResourceRepresentation();
        resourceRep.setId(resource.getId());
        resourceRep.setName(resource.getName());
        resourceRep.setUris(resource.getUris());
        Set<String> scopes = resource.getScopes();
        Set<ScopeRepresentation> scopeRepresentations = new HashSet<>();
        scopes.forEach(scope -> scopeRepresentations.add(new ScopeRepresentation(scope)));
        resourceRep.setScopes(scopeRepresentations);
        resourceRep.setDisplayName(resource.getDisplayName());
        resourceRep.setType(resource.getType());
        return resourceRep;
    }
}
