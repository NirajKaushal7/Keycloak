//package com.Student.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Component
//public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthConverter.class);
//    @Override
//    public AbstractAuthenticationToken convert(Jwt jwt) {
//        Collection<GrantedAuthority> roles = extractAuthorities(jwt);
//        return new JwtAuthenticationToken(jwt, roles);
//    }
//
//    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
//        if(jwt.getClaim(SecurityConstants.REALM_ACCESS_CLAIM) != null) {
//            Map<String, Object> realmAccess = jwt.getClaim(SecurityConstants.REALM_ACCESS_CLAIM);
//            ObjectMapper mapper = new ObjectMapper();
//            List<String> keycloakRoles = mapper.convertValue(realmAccess.get(SecurityConstants.ROLES), new TypeReference<List<String>>(){});
//            List<GrantedAuthority> roles = new ArrayList<>();
//
//            for (String keycloakRole : keycloakRoles) {
// /*
// The "ROLE_" prefix is a convention used in Spring Security to distinguish roles from other types of authorities
// In Spring Security, when you use SimpleGrantedAuthority objects to represent roles.
// This convention helps to distinguish roles from other types of authorities and aligns with Spring Security's expectations for role-based access control*/
//                roles.add(new SimpleGrantedAuthority(SecurityConstants.ROLE_PREFIX + keycloakRole));
//            }
//            logger.info("roles => {}",roles);
//            return roles;
//        }
//        return new ArrayList<>();
//    }
//}