package com.linkcheck.service;

import com.linkcheck.model.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

@Service
public class LinkCheckService {

    private final User user;
    private final EmailService emailService;
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(LinkCheckService.class.getName());

    public LinkCheckService(User user, EmailService emailService, UserService userService) {
        this.user = user;
        this.emailService = emailService;
        this.userService = userService;
    }

   @Scheduled(cron = "0 */10 * * * *")
    public boolean linkCheck() throws Exception {
        try {
            String urlString ="link";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                logger.info("Link is full");
                List<User> users = userService.getAllUsers();
                if (!users.isEmpty()) {
                    User user = users.get(0);
                    emailService.sendEmail(user);
                    logger.info("Email sent to user: " + user.getEmail());
                }
                logger.info("responsecode" + responseCode);
                return true;
            } else {
                logger.info("Response code is not OK " + responseCode);
                return false;
            }
        } catch (Exception e) {
            logger.info("An error occurred during link checking: " + e.getMessage());
            throw new Exception("Link check task failed", e);
        }
    }


}
