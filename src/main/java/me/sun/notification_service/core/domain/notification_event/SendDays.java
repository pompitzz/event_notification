package me.sun.notification_service.core.domain.notification_event;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SendDays {
    private boolean sendMonday = false;
    private boolean sendTuesDay = false;
    private boolean sendWednesday = false;
    private boolean sendThursday = false;
    private boolean sendFriday = false;
    private boolean sendSaturday = false;
    private boolean sendSunday = false;
}
