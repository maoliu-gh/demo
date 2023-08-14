package com.example.demo.photo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name= "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4877710499191965833L;

	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "password", nullable = false)
	private String password;
}
