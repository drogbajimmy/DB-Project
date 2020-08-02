package subscription.dao;

import subscription.entity.Subscription;

import java.util.List;

public interface SubscriptionDao {

    List<Subscription> selectByCustomerId(Integer id);

    void deleteByPrimaryKey(Integer id);

    void insert(Subscription entity);
}
