package com.example.demo.photo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.model.SampleModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Document
@Table (name= "photostat",
		indexes = {@Index (name = "photostat_nameuser_index", columnList ="name, user", unique = true),
					@Index (name = "photostat_id2_index", columnList ="id2", unique = true),
					@Index (name = "photostat_datename_index", columnList ="date, name", unique = false),})
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)

public class PhotoStat implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -7805755776966699224L;
	
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	@Column(name = "id2", nullable = false)
	private String id2;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "date", nullable = false)
	private Date date;
	@Column(name = "user", nullable = false)
	private String user;
}
