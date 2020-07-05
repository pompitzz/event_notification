package me.sun.notification_service.core.domain.notification_event;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SendDays {
    private boolean sendMonday;
    private boolean sendTuesDay;
    private boolean sendWednesday;
    private boolean sendThursday;
    private boolean sendFriday;
    private boolean sendSaturday;
    private boolean sendSunday;

    public static SendDays sendWeekday() {
        final SendDays sendDays = new SendDays();
        sendDays.sendMonday = true;
        sendDays.sendTuesDay = true;
        sendDays.sendWednesday = true;
        sendDays.sendThursday = true;
        sendDays.sendFriday = true;
        return sendDays;
    }

    public static SendDays sendAllday() {
        final SendDays sendDays = new SendDays();
        sendDays.sendMonday = true;
        sendDays.sendTuesDay = true;
        sendDays.sendWednesday = true;
        sendDays.sendThursday = true;
        sendDays.sendFriday = true;
        sendDays.sendSaturday = true;
        sendDays.sendSunday = true;
        return sendDays;
    }
}
