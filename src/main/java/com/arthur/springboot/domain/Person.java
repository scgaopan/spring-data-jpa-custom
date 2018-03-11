package com.arthur.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Person.withNameAndAddressNamedQuery",
     query="select p from Person p where p.name=?1 and address=?2")
public class Person {

	
	@Id
	//@GeneratedValue注解默认使用主健生成方式为自增，hibernate会为我们自动生成一个名为HIBERNATE_SEQUENCE的序列。
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Integer age;
	private String address;
	
	
	public Person(){
		
	}
	
	public Person(String name,String address,Integer age) {
		this.name=name;
		this.address=address;
		this.age=age;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
