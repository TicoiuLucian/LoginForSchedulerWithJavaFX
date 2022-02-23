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
@Table(name = "message_temp")
public class MessageTemp extends MessageParent {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    public MessageTemp(Date createDate, String uuid, String content, String sender) {
        super(uuid, content, sender);
        this.createDate = createDate;
    }

}
