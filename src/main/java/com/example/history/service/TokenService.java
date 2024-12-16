package com.example.history.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class TokenService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getUsernameFromToken(String token) {
        String url = "http://localhost:8081/authentication-server/api/validate-token";

        // Construire la requête avec le token
        JSONObject request = new JSONObject();
        request.put("token", token);

        // Faire un appel POST pour valider le token
        ResponseEntity<String> response = restTemplate.postForEntity(url, request.toString(), String.class);

        // Extraire le nom d'utilisateur de la réponse JSON
        JSONObject jsonResponse = new JSONObject(response.getBody());
        if (jsonResponse.getBoolean("isValid")) {
            return jsonResponse.getString("username");
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }
}
