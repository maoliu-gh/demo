package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SampleModel;
import com.example.demo.photo.model.PhotoStat;

public interface PhotoStatRepository extends JpaRepository<PhotoStat, String>{
	PhotoStat findById2(String id2);
	List<PhotoStat> findByName (String name);
	List<PhotoStat> findByDate (Date date);
	List<PhotoStat> findByUser (String user);
	PhotoStat findByNameAndUser (String name, String user);
	List<PhotoStat> findByUserAndDate (String user, Date date);
	
}
