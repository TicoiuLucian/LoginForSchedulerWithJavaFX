package ro.itschool.scheduler.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.itschool.scheduler.entity.Message;
import ro.itschool.scheduler.entity.MessageRejected;
import ro.itschool.scheduler.entity.MessageTemp;
import ro.itschool.scheduler.repository.MessageRepository;
import ro.itschool.scheduler.util.MessageStatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MessageScheduler {

    @Autowired
    MessageRepository<MessageTemp, String> tempRepository;

    @Autowired
    MessageRepository<Message, String> repository;

    @Autowired
    MessageRepository<MessageRejected, String> rejectedRepository;

    @Scheduled(cron = "0 0 * ? * *")
    public void scheduleFixedDelayTask() {
        log.info("Cronjob has started");
        //get all from temp table
        List<MessageTemp> temporaries = new ArrayList<>();
        for (MessageTemp temp : tempRepository.getAllTemporaryMessages()) {
            temporaries.add(temp);
        }

        //validate messages
        temporaries.forEach(msg -> {
            validateAndMove(msg);
            tempRepository.delete(msg);
        });

        log.info("Cronjob has ended");
    }

    private void validateAndMove(MessageTemp msg) {
        final String messageStatus = validateMessage(msg).label;
        if (messageStatus.equalsIgnoreCase(MessageStatusEnum.VALID.label)) {
            Message message = new Message(msg.getUuid(), msg.getContent(), msg.getSender(), new Date());
            repository.save(message);
            log.info("Message with id: {} was approved", msg.getUuid());
        } else {
            MessageRejected rejected = new MessageRejected(msg.getUuid(), msg.getContent(), msg.getSender()
                    , messageStatus);
            log.warn("Message with id:{} was rejected because: {}", msg.getUuid(), messageStatus);
            rejectedRepository.save(rejected);
        }
    }

    private MessageStatusEnum validateMessage(MessageTemp msg) {
        if (msg.getContent().length() > 200)
            return MessageStatusEnum.REJECTED_TOO_LONG;
        if (msg.getContent().length() < 10)
            return MessageStatusEnum.REJECTED_TOO_SHORT;
        if (msg.getContent().contains("bomb"))
            return MessageStatusEnum.REJECTED_BAD_WORD;
        return MessageStatusEnum.VALID;
    }

}
