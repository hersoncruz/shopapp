package com.thinkcloudgroup.shopapp.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.thinkcloudgroup.shopapp.objects.UserCompany;

@RepositoryRestResource(collectionResourceRel = "userCompany", path = "userCompany")
public interface UserCompanyRepository extends MongoRepository<UserCompany, String>{

	List<UserCompany> findByUserId(String userId);
}
