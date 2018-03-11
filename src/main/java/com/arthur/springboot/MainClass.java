package com.arthur.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.arthur.springboot.dao.CustomRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass=CustomRepositoryFactoryBean.class)
public class MainClass {
	
	public static void main(String[] args) {
		SpringApplication.run(MainClass.class, args);
	}

}
