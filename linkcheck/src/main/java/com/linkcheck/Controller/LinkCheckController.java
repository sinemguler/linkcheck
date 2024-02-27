package com.linkcheck.Controller;

import com.linkcheck.service.LinkCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/linkcheck")
public class LinkCheckController {
    private static final Logger logger = Logger.getLogger(LinkCheckController.class.getName());
    private final LinkCheckService linkCheckService;

    public LinkCheckController(LinkCheckService linkCheckService) {
        this.linkCheckService = linkCheckService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkLink() {
        try {
            boolean isLinkFull = linkCheckService.linkCheck();
            logger.info("isLinkFull " + isLinkFull);

            if (isLinkFull) {
                return ResponseEntity.ok("Link is full. Email has been sent to the user.");
            } else {
                return ResponseEntity.ok("Link is not full.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while checking link.");
        }
    }

}
