package com.example.parent;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
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
public class ParentController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/parent")
    @Observed(
            name = "user.name",
            contextualName = "parent_service",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public String callChild() {
        log.info("this is parent service controller....");
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8082/child-service/child", HttpMethod.GET,
                null, String.class);
        return response.getBody();
    }
}
