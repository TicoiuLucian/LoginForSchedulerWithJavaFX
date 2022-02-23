package ro.itschool.scheduler.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.scheduler.entity.Message;
import ro.itschool.scheduler.service.MessageService;

import java.util.List;

@PreAuthorize("hasAnyRole('USER','ADMIN')")
@RestController
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/add-message")
    public void saveMessage(@RequestBody Message message) {
        messageService.save(message);
    }
}
