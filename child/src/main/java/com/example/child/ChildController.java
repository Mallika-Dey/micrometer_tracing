package com.example.child;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mallika Dey
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ChildController {
    private final ChildService childService;

    @GetMapping("/child")
    @Observed(
            name = "user.name",
            contextualName = "child_service",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public String showService() {
        log.info("this is child service controller....");
        return childService.childService();
    }
}
