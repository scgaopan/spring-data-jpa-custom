package com.arthur.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arthur.springboot.domain.Person;

public interface PersonNewRepository  extends CustomRepository<Person, Long>{

	//使用方法查询，接收一个name参数
		List<Person> findByAddress(String name);
		//使用方法查询，接收一个name参数,address参数
		Person findByNameAndAddress(String name,String address);
		
		//使用Quer查询
		@Query("select p from Person p where p.name=:name and p.address=:address")
		Person withNameAndAddressQuery(@Param("name") String name,
				@Param("address") String address);
		
		//使用@NamedQuery查询
		Person withNameAndAddressNamedQuery(String name,String address);
		

}
