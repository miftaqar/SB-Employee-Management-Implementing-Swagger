package com.ifteqars.emp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employeeDirectory")
@ApiModel(description = "All details about the Employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The Database genereated Employee ID")
	private Integer employeeID;

	@Column(name = "firstName")
	@ApiModelProperty(notes = "Employee First Name")
	private String firstName;

	@Column(name = "lastName")
	@ApiModelProperty(notes = "Employee Last Name")
	private String lastName;

	@Column(name = "emailID")
	@ApiModelProperty(notes = "Employee Email ID")
	private String emailID;

	
}
