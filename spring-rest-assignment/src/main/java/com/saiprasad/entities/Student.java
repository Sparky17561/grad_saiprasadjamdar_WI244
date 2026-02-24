package com.saiprasad.entities;

import jakarta.persistence.*;

@Entity // to say this is a table .. map this class with a table with same name Employee (it table name is diff then u can map with @Table("EMP")
public class Student {
	
	@Id// for primary key
	private int regNo;
	public int getRegNo() {
		return regNo;
	}
	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Student(int regNo, int rollNo, String name, int standard, String school, String gender, float percentage,
			String designation) {
		super();
		this.regNo = regNo;
		this.rollNo = rollNo;
		this.name = name;
		this.standard = standard;
		this.school = school;
		this.gender = gender;
		this.percentage = percentage;
		
	}
	private Integer rollNo; 
	private String name;
	@Column // by default will give same name only 
	private Integer standard;
	private String school;
	private String gender;
	private Float percentage;

	public Student() {
		
	}




 

}
