package subscription.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import subscription.dao.SubscriptionDao;
import subscription.entity.Subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    public List<Subscription> getSubscriptionByCustomerId(int customerId) {

        return subscriptionDao.selectByCustomerId(customerId);
    }

    public Map<String, String> getUnsubscribedService(Map<String, String> allServices, List<Subscription> subscriptions) {

        Map<String, String> unsubscribedService = new HashMap<>();
        unsubscribedService.putAll(allServices);

        subscriptions.stream().forEach(s -> {
            unsubscribedService.remove(String.valueOf(s.getServiceId()));
        });

        return unsubscribedService;
    }

    public void removeSubscription(int id) {
        subscriptionDao.deleteByPrimaryKey(id);
    }

    public void subscribeNewService(Subscription entity) {
        subscriptionDao.insert(entity);
    }
}
