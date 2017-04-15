package com.thinkcloudgroup.shopapp.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.thinkcloudgroup.shopapp.objects.Company;

@RepositoryRestResource(collectionResourceRel = "company", path = "company")
public interface CompanyRepository extends MongoRepository<Company, String>{
	List<Company> findByName(@Param("name") String name);
}
