package com.example.demo.photo.dao;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

public interface PhotoStorage {
	public String store(InputStream inputStream, String FileName, String contentType, DBObject metaData);
	public GridFSDBFile getById(String id);
	//public GridFSDBFile getLatestByFilename(String fileName);
	//public void deleteById (String id);
	public boolean deleteById(String id);
	public void deleteAll();
	public List<GridFSDBFile> getAllByFilename (String fileName);
	public List<GridFSDBFile> getAll();
	//public void deleteByDate (Date date);
	public void deleteByDate(Date date1, Date date2);
}
