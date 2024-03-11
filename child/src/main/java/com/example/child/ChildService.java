package com.example.child;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Mallika Dey
 */
@Service
@Slf4j
public class ChildService {
    public String childService() {
        log.info("childservice executing................");
        return testChildService();
    }

    public String testChildService() {
        log.info("test child service executing................");
        return "child service.......";
    }
}
