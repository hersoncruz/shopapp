package com.thinkcloudgroup.shopapp.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.thinkcloudgroup.shopapp.objects.Category;

@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends MongoRepository<Category, String>{
	List<Category> findByName(@Param("name") String name);
}
