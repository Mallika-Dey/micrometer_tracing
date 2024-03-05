package com.example.micrometer;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mallika Dey
 */
@RestController
@Slf4j
public class MicrometerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/call/parent")
    @Observed(
            name = "user.name",
            contextualName = "micrometer_service",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public String callParent() {
        log.info("micrometer is called....");
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8081/parent-service/parent",
                HttpMethod.GET, null, String.class);
        return response.getBody();
    }
}
