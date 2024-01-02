package com.Student.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {
    private String clientId;
    private String name;
    private String secret;
    private List<String> redirectUris;
    private List<String> webOrigins;}
