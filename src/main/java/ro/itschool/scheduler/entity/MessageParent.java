package ro.itschool.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MessageParent {
    @Id
    protected String uuid;

    protected String content;

    protected String sender;
}
