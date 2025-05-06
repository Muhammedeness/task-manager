package com.eteration_project.eteration_project.keycloak.service;

import com.eteration_project.eteration_project.common.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.user.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    @Value("${keycloak.admin.access.url}")
    private String keycloakAdminAccessUrl;

    @Value("${keycloak.get.user.url}")
    private String keycloakListUserUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin.client-id}")
    private String adminClientId;

    @Value("${keycloak.admin.client-secret}")
    private String adminClientSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = Logger.getLogger(KeycloakService.class.getName());

   /* public  String getUserIdFromToken(){

        JwtAuthenticationToken jwtAuthenticationToken  = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) jwtAuthenticationToken.getCredentials();
        return jwt.getSubject();
    }*/

    public String getAdminAccessToken() {
        String tokenUrl = keycloakAdminAccessUrl;

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", adminClientId);
        formData.add("client_secret", adminClientSecret);
        formData.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        Map<String, Object> response = restTemplate.postForObject(tokenUrl, request, Map.class);

        return (String) response.get("access_token");
    }

    /**
     * BU addUserToList METHODU username ALIR getAdminAccessToken METHODUNU ÇAGIRARAK ÖNCE BİR ADMİN ACCESS TOKEN OLUŞTURUR
     * user_email i URL'ye EKLEYEREK  /admin/realms/spring-boot-realm/users?email= KEYCLOAK ENDPOİNTİNE İSTEK ATAR.
     * RESPONSE OLARAK DÖNEN DATAYI LİSTEYE ATAR.
     *
     * @param userEmail aranacak kullanıcının  email adresidir.
     * @author Muhammed Enes Selvi
     * */

    public List<Map<String , Object>> addUserToList(String  userEmail){
        String adminAccessToken = getAdminAccessToken();
        String url = keycloakListUserUrl + userEmail;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminAccessToken); //admin access token ile user listelenir
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

/**
 * getUserDetails METHODU addUserToList METHODUNU ÇAĞIRIP LİSTEYİ OKUR
 * UserDetailsDTO İLE USER DETAYLARINI DÖNDÜRÜR
 * @param userEmail aranacak kullanıcının  email adresidir.
 * @author Muhammed Enes Selvi
 * */
    public UserDetailsDTO getUserDetails(String userEmail){
        List<Map<String , Object>> users = this.addUserToList(userEmail);

        if (users.isEmpty()) {
            throw  new CustomNotFoundException("error.user.not.found");
        }
        String id = "", username = "" , firstName = "", lastName = "" , email = "";
        for(Map<String , Object> user : users){
             id = (String) user.get("id");
             username = (String) user.get("username");
             firstName = (String) user.get("firstName");
             lastName = (String) user.get("lastName");
             email = (String) user.get("email");
        }
        return (new UserDetailsDTO(firstName , lastName , email , id , username));
    }
}
