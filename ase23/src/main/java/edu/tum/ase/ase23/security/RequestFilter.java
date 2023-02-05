package edu.tum.ase.ase23.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tum.ase.ase23.util.MultiReadHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

//    private final RestTemplate restTemplate;

    @Value("${edu.tum.ase.ase23.app.authServer}")
    private String authServer;

//    public RequestFilter(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MultiReadHttpServletRequest servletRequest = new MultiReadHttpServletRequest(request);
        try {
            // Send a request to the authentication server to validate the authentication
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Max-Age", "3600");

            if (!request.getMethod().equals("OPTIONS")) {


                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", request.getHeader("Authorization"));
                headers.set("Origin", "http://localhost:8080");
                HttpEntity<Map<String, Object>> entity;
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object> authResponse;
                if (!request.getMethod().equals("GET")) {
                    headers.setContentType(MediaType.valueOf(request.getHeader("Content-Type")));
                    headers.set("x-authentication", request.getHeader("x-authentication"));
                    String bodyContent = servletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                    JSONObject bodyContentJson = new JSONObject(bodyContent);
                    servletRequest.setAttribute("body", bodyContentJson);
                    entity = new HttpEntity<>(bodyContentJson.toMap(), headers);
                    authResponse = restTemplate.exchange(
                            authServer + request.getRequestURI(),
                            HttpMethod.valueOf(request.getMethod()),
                            entity,
                            new ParameterizedTypeReference<Object>() {
                            });
                } else {
                    entity = new HttpEntity<>(null, headers);
                    authResponse = restTemplate.exchange(
                            authServer + request.getRequestURI(),
                            HttpMethod.valueOf(request.getMethod()),
                            entity,
                            new ParameterizedTypeReference<Object>() {
                            }
                    );
                }


                response.setContentType(MediaType.APPLICATION_JSON.toString());
                if (authResponse.getStatusCode().equals(HttpStatus.OK)) {
                    response.setStatus(HttpStatus.OK.value());
                    servletRequest.setAttribute("body",authResponse.getBody());
//                    final ObjectMapper mapper = new ObjectMapper();
//                    mapper.writeValue(response.getOutputStream(), authResponse.getBody());

                }
            }
        } catch (Exception e) {
//            logger.error("Error during authentication", e);
            JSONObject json = new JSONObject(e.getMessage().substring(7, e.getMessage().length() - 1));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(Integer.parseInt(e.getMessage().substring(0, 3)));
//            request.setAttribute("body",json);
            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), json.toMap());
            logger.info("qasdasd");
        }

        filterChain.doFilter(new MultiReadHttpServletRequest(servletRequest), response);
    }


}
