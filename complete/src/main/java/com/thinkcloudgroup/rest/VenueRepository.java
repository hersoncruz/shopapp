package com.thinkcloudgroup.rest;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.thinkcloudgroup.shopapp.objects.Venue;

@RepositoryRestResource(collectionResourceRel = "venue", path = "venue")
public interface VenueRepository extends MongoRepository<Venue, String>{
	List<Venue> findByName(@Param("name") String name);
}
