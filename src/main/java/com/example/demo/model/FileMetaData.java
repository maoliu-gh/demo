package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetaData implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 7972320152460903571L;
	
	private String fileName;
	private String fileExt;
	private String fileDescription;
	private String filePath;
	private String year;
	private String tags;
	
}
