package com.arthur.springboot.dao;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 继承了JpaRepository,让我们具务了JpaRepository所提供的所有方法；
 * 继承了JpaSpecificationExecutor，让我们具务使用Specification的能力
 * @author gaopan
 *
 * @param <T>
 * @param <ID>
 */

//@NoRepositoryBean 注解告知 Spring Data: 该实现类不是一个 Repository
//注意中间的存储库接口由@NoRepositoryBean注解表示。确保你添加了这个注解给所有的存储库接口，这样Spring Data不会在运行时创建实例。
@NoRepositoryBean
public interface CustomRepository<T,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {

	Page<T> findByAuto(T examle,Pageable pageable);
}
