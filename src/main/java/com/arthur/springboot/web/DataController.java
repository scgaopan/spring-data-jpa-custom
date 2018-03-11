package com.arthur.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.springboot.dao.PersonNewRepository;
import com.arthur.springboot.dao.PersonRepository;
import com.arthur.springboot.domain.Person;

@RestController
public class DataController {
	
	//@Autowired
	//PersonRepository personRepository;
	
	@Autowired
	PersonNewRepository personNewRepository;
	
//	@RequestMapping("/save")
//	public Person save(String name,String address ,Integer age){
//		
//		Person p=personRepository.save(new Person(name,address,age));
//		return p;
//	}
//	
//	
//	@RequestMapping("/q1")
//	public List<Person> q1(String address){
//		return personRepository.findByAddress(address);
//	}
//	
//	@RequestMapping("/q2")
//	public Person q2(String name,String address){
//		return personRepository.findByNameAndAddress(name, address);
//	}
//	
//	@RequestMapping("/q3")
//	public Person q3(String name,String address){
//		return personRepository.withNameAndAddressQuery(name, address);
//	}
//	
//	
//
//	@RequestMapping("/q4")
//	public Person q4(String name,String address){
//		return personRepository.withNameAndAddressNamedQuery(name, address);
//	}
//	
//	@RequestMapping("/sort")
//	public List<Person> sort(){
//		List<Person> pepole=personRepository.findAll(new Sort(Direction.ASC, "age"));
//		return pepole; 
//	}
//	
//	@RequestMapping("page")
//	public Page<Person> page(){
//		Page<Person> page=personRepository.findAll(new PageRequest(0, 1));
//		return page;
//	}
	
	@RequestMapping("auto")
	public Page<Person> auto(Person person){
		Page<Person> page=personNewRepository.findByAuto(person, new PageRequest(0, 1));
		return page;
	}
	
	

}
