package ro.itschool.scheduler.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@ToString
@Table(name = "message")
public class Message extends MessageParent {

    @Temporal(TemporalType.TIMESTAMP)
    Date creationTime;

    public Message(String uuid, String content, String sender, Date creationTime) {
        super(uuid, content, sender);
        this.creationTime = creationTime;
    }
}
