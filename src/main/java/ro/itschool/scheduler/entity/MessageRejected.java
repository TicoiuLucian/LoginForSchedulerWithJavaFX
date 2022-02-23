package ro.itschool.scheduler.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@ToString
@Table(name = "message_rejected")
public class MessageRejected extends MessageParent {

    private String messageStatus;

    public MessageRejected(String uuid, String content, String sender, String messageStatus) {
        super(uuid, content, sender);
        this.messageStatus = messageStatus;
    }

}
