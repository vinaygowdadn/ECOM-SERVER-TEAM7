package com.bootA.ecom_server.repos;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.bootA.ecom_server.models.Order;

public interface OrderRepo extends MongoRepository<Order, String> { 
    List<Order> findByUserId(String userId); // <-- Must match the field name in Order model
}
