package com.Student.service;

import com.Student.dto.Permission;
import org.keycloak.representations.idm.authorization.ResourcePermissionRepresentation;
import org.keycloak.representations.idm.authorization.ScopePermissionRepresentation;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PermissionService{
    public Permission mapResourcePermission(ResourcePermissionRepresentation resourcePermissionRep) {
        Permission permission = new Permission();
        permission.setName(resourcePermissionRep.getName());
        permission.setDescription(resourcePermissionRep.getDescription());
        permission.setResources(resourcePermissionRep.getResources());
        permission.setPolicies(resourcePermissionRep.getPolicies());
        return permission;
    }

    public ResourcePermissionRepresentation mapResourcePermissionRep(Permission resourcePermission) {
        ResourcePermissionRepresentation resourcePermissionRep = new ResourcePermissionRepresentation ();
        resourcePermissionRep.setName(resourcePermission.getName());
        resourcePermissionRep.setDescription(resourcePermission.getDescription());
        resourcePermissionRep.setResources(resourcePermission.getResources());
        resourcePermissionRep.setPolicies(resourcePermission.getPolicies());
        return resourcePermissionRep;    }

    public ScopePermissionRepresentation mapScopePermissionRep(Permission scopePermission) {
        ScopePermissionRepresentation scopePermissionRep = new ScopePermissionRepresentation ();
        scopePermissionRep.setName(scopePermission.getName());
        scopePermissionRep.setDescription(scopePermission.getDescription());
        scopePermissionRep.setResources(scopePermission.getResources());
        scopePermissionRep.setPolicies(scopePermission.getPolicies());
        return scopePermissionRep;    }
}
