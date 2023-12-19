package nl.fontysS3_project.controllers;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.domain.NotificationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4173", "http://localhost:5173", "http://localhost:4174" })
@RequestMapping("notifications")
public class NotificationsController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Void> sendNotificationToUsers(@RequestBody NotificationMessage message) {
        messagingTemplate.convertAndSend("/topic/publicmessages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
