package com.arthur.springboot.specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;

import antlr.StringUtils;
/**
 * 用Specification和自定义Repository实现一处自动模糊查询
 * @author gaopan
 *
 */
public class CustomSpecification {
	
	//定义一个返回Specification的方法，接收的参数是entityManager和当前查询条件对象
	public static <T> Specification<T> byAuto(final EntityManager entityManager,
			final T example){
		final Class<T> type=( Class<T>) example.getClass();
		
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates=new ArrayList<>();//predicate列表存储的查询条件
				
				//获得实体类的EntityType,我们可以从EntityType获得实体类的属性
				EntityType<T> entity=entityManager.getMetamodel().entity(type);
				
				//对实体类的所有属性做循环
				for(Attribute<T, ?> attr:entity.getDeclaredAttributes()){
					Object attrValue=getValue(example,attr);
					if(attrValue!=null){
						if(attr.getJavaType()==String.class){//当前属性是否是字符串类型
							if(!org.springframework.util.StringUtils.isEmpty(attrValue)){
								predicates.add(cb.like(root.get(attribute(entity,attr.getName(),String.class)),pattern((String)attrValue)));
							}
							
						}else{
							System.out.println(attrValue.getClass());
							System.out.println(attr.getJavaType());
							predicates.add(cb.equal(root.get(attribute(entity,attr.getName(),attrValue.getClass())), attrValue));
						}
					}
					
				}
				return predicates.isEmpty()?cb.conjunction():cb.and(toArray(predicates, Predicate.class));
			}
			
			//通过反射获得实体类对象对应属性的属性值
			private  <T>  Object getValue(T example,Attribute<T, ?> attr){
				return ReflectionUtils.getField((Field)attr.getJavaMember(), example);
			}
			//获得实体类的当前属性的SingularAttribute,SinglarAttribute包含的是实体类的某个单独属性。
			private <E,T> SingularAttribute<T, E> attribute(EntityType<T> entity,String fieldName,Class<E> fieldClass){
				return entity.getDeclaredSingularAttribute(fieldName,fieldClass);
			}
		};
	}
	
	
	static private String pattern(String str){
		return "%"+str+"%";
	}
	
	
	

}
