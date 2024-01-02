package com.Student.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class Permission {
    private String name;
    private String description;
    private Set<String> scopes;
    private Set<String> resources;
    private Set<String> policies;
}
