package application.entities.enums;

import application.entities.SubscriptionEntity;
import application.entities.interfaces.StatusChanger;

public enum SubscriptionStatus implements StatusChanger {
    ACTIVE(e -> e.activate()), CANCELED(e -> e.cancel()), DEACTIVATED(e -> e.deactivate()), PENDINGFORDELETION(
            e -> e.putIntoDeletionQueue());

    private StatusChanger changer;

    private SubscriptionStatus(StatusChanger changer) {
        this.changer = changer;
    }

    @Override
    public void change(SubscriptionEntity subscription) {
        changer.change(subscription);
    }
}
