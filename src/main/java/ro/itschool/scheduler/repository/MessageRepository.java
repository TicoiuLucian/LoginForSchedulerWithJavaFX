package ro.itschool.scheduler.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.scheduler.entity.Message;
import ro.itschool.scheduler.entity.MessageParent;
import ro.itschool.scheduler.entity.MessageRejected;
import ro.itschool.scheduler.entity.MessageTemp;

import java.io.Serializable;
import java.util.List;

@Repository
public interface MessageRepository<T extends MessageParent, I extends Serializable> extends CrudRepository<T, I> {

    @Query("from Message")
    List<Message> getAllValidMessages();

    @Query("from MessageRejected")
    List<MessageRejected> getAllRejectedMessages();

    @Query("from MessageTemp")
    List<MessageTemp> getAllTemporaryMessages();

    @Query(value = "Select * from message where sender = ?1", nativeQuery = true)
    List<Message> getAllMessagesForUsername(String username);

}
