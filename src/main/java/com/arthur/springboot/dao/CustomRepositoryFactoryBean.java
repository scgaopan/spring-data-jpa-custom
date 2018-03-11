package com.arthur.springboot.dao;

import java.io.Serializable;  
 
import javax.persistence.EntityManager;  
 
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;  
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;  
import org.springframework.data.repository.core.RepositoryMetadata;  
import org.springframework.data.repository.core.support.RepositoryFactorySupport;  
 
/** 
* 创建一个自定义的FactoryBean去替代默认的工厂类 
* @author xiaowen 
* @date 2016年5月30日 
* @ version 1.0 
* @param <R> 
* @param <T> 
* @param <I> 
*/  
public class CustomRepositoryFactoryBean <R extends JpaRepository<T, I>, T, I extends Serializable>  
extends JpaRepositoryFactoryBean<R, T, I> {  
     
   public CustomRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
		// TODO Auto-generated constructor stub
	}

@SuppressWarnings("rawtypes")  
   protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {  
       return new CustomRepositoryFactory(em);  
   }  
 
   private static class CustomRepositoryFactory<T, I extends Serializable>  
           extends JpaRepositoryFactory {  
 
       private final EntityManager em;  
 
       public CustomRepositoryFactory(EntityManager em) {  
           super(em);  
           this.em = em;  
       }  
 
       @SuppressWarnings("unchecked")  
       protected Object getTargetRepository(RepositoryMetadata metadata) {  
           return new CustomRepositoryImple<T, I>(  
                   (Class<T>) metadata.getDomainType(), em);  
       }  
 
       protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {  
           return CustomRepositoryImple.class;  
       }  
   }  
 
}  
