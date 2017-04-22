package com.thinkcloudgroup.shopapp.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.thinkcloudgroup.shopapp.objects.UserAddress;

@RepositoryRestResource(collectionResourceRel = "userAddress", path = "userAddress")
public interface UserAddressRepository extends MongoRepository<UserAddress, String>{
	List<UserAddress> findByUserId(@Param("userId") String userId);
}
