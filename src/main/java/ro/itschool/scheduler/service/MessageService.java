package ro.itschool.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.scheduler.entity.Message;
import ro.itschool.scheduler.entity.MessageTemp;
import ro.itschool.scheduler.repository.MessageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    MessageRepository<Message, String> messageRepository;

    @Autowired
    MessageRepository<MessageTemp, String> tempRepository;

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public void save(Message message) {
        MessageTemp messageTemp = new MessageTemp(new Date(), UUID.randomUUID().toString(), message.getContent(), message.getSender());
        tempRepository.save(messageTemp);
    }

    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }

    public List<Message> getAllMessagesForUsername(String username){
        return messageRepository.getAllMessagesForUsername(username);
    }
}
