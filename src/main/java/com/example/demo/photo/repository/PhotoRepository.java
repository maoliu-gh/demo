package com.example.demo.photo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.photo.model.PhotoStat;

public interface PhotoRepository extends MongoRepository<PhotoStat, String>{
	

}
