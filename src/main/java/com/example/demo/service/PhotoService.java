package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.demo.model.FileMetaData;
import com.example.demo.model.SampleModel;
import com.example.demo.photo.model.PhotoStat;

public interface PhotoService {
	public List<S3ObjectSummary> getList(); 
	public void uploadPhoto(String fileName, InputStream input, FileMetaData fileData) throws IOException;
	public InputStream downloadPhoto(String fileName);
	public void deletePhoto(String fileName);
	public Map<String, String> getFileMetaData(String key);
}
