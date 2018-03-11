package com.arthur.springboot.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.arthur.springboot.specification.CustomSpecification;
/**
 * 此类继承了SimpleJpaRepository 让我们可以使用SimpleJpaRepository的方法
 * @author gaopan
 *
 * @param <T>
 * @param <ID>
 */
public class CustomRepositoryImple<T,ID extends Serializable> extends SimpleJpaRepository<T, ID>
			implements CustomRepository<T, ID>{

	private final EntityManager entityManager;

	public CustomRepositoryImple(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager=em;
	}
	
	public CustomRepositoryImple(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager=entityManager;
	}

	@Override
	public Page<T> findByAuto(T examle, Pageable pageable) {
		// TODO Auto-generated method stub
		return findAll(CustomSpecification.byAuto(entityManager, examle),pageable);
	}

}
