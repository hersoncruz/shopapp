package com.thinkcloudgroup.rest;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.thinkcloudgroup.shopapp.objects.ProductMeta;
import com.thinkcloudgroup.shopapp.objects.Product;

@RepositoryRestResource(collectionResourceRel = "productMeta", path = "productMeta")
public interface ProductMetaRepository extends MongoRepository<ProductMeta, String>{
	List<Product> findByProductId(@Param("productId") String productId);
}
