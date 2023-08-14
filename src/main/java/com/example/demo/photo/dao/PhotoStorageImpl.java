/*package com.example.demo.photo.dao;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class PhotoStorageImpl implements PhotoStorage{
	
	private GridFsTemplate gridFsTemplate;
	
	@Autowired
	public PhotoStorageImpl(GridFsTemplate gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
	}
	@Override
	public String store(InputStream inputStream, String FileName, String contentType, DBObject metaData) {
		return gridFsTemplate.store(inputStream, FileName, contentType, metaData)
				.getId().toString();
	}

	@Override
	public GridFSDBFile getById(String id) {
		return gridFsTemplate.findOne(new Query(GridFsCriteria.where("_id").is(id)));
	}

	@Override
	public boolean deleteById(String id) {
		gridFsTemplate.delete(new Query(GridFsCriteria.where("_id").is(id)));
		return gridFsTemplate.findOne(new Query(GridFsCriteria.where("_id").is(id)))==null;
	
	}

	@Override
	public void deleteAll() {
		gridFsTemplate.delete(null);
	}

	@Override
	public List<GridFSDBFile> getAllByFilename(String fileName) {
		return gridFsTemplate.find(new Query(GridFsCriteria.whereFilename().is(fileName)));
	}

	@Override
	public List<GridFSDBFile> getAll() {
		return gridFsTemplate.find(null);
	}

	@Override
	public void deleteByDate(Date date1, Date date2) {
		gridFsTemplate.delete(new Query(GridFsCriteria.where("uploadDate").gte(date1).andOperator(GridFsCriteria.where("uploadDate").lte(date2))));
		
	}

	
}
*/