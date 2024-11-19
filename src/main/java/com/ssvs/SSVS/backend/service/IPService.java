package com.ssvs.SSVS.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class IPService {

    private static final String IPIFY_URL = "https://api.ipify.org?format=json";

    public String obtenerIPPublica() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(IPIFY_URL, String.class);
            JSONObject json = new JSONObject(response);
            return json.getString("ip");
        } catch (Exception e) {
            e.printStackTrace();
            return "No se pudo obtener la IP pública";
        }
    }
}
