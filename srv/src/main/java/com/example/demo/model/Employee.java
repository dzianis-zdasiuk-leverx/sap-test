package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
	@Id
	@Column(name ="id", unique=true)
	private long id;
	@Column(name ="firstName")
	private String firstName;
	@Column(name ="lastName")
	private String lastName;
	@Column(name ="email", unique=true)
	private String email;
	@Column(name ="contact",unique=true)
	private String contact;
}