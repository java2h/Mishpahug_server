package application.entities.interfaces;

import application.entities.SubscriptionEntity;

public interface StatusChanger {
    void change(SubscriptionEntity subscription);
}
