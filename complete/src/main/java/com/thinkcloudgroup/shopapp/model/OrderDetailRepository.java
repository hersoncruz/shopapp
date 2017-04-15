package com.thinkcloudgroup.shopapp.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.thinkcloudgroup.shopapp.objects.OrderDetail;

@RepositoryRestResource(collectionResourceRel = "orderDetail", path = "orderDetail")
public interface OrderDetailRepository extends MongoRepository<OrderDetail, String>{
	List<OrderDetail> findByOrderId(@Param("orderId") String orderId);
}
