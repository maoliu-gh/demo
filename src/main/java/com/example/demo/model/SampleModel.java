package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -60128321405759081L;
	
	@Id
	@GeneratedValue
	//@Column (name = "pic_id")
	private Integer picId;
	private String name;

}
