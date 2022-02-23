package ro.itschool.scheduler.util;

public enum MessageStatusEnum {
    VALID("valid"),
    REJECTED_TOO_SHORT("message is too short"),
    REJECTED_TOO_LONG("message is too long"),
    REJECTED_BAD_WORD("message contains a bad word");


    public final String label;

    MessageStatusEnum(String label) {
        this.label = label;
    }
}
