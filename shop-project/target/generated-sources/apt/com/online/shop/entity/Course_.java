package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@StaticMetamodel(Course.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Course_ {

	public static final String CREATED_AT = "createdAt";
	public static final String COURSE_PLAN = "coursePlan";
	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CATEGORIES = "categories";
	public static final String TITLE = "title";

	
	/**
	 * @see com.online.shop.entity.Course#createdAt
	 **/
	public static volatile SingularAttribute<Course, LocalDateTime> createdAt;
	
	/**
	 * @see com.online.shop.entity.Course#coursePlan
	 **/
	public static volatile SingularAttribute<Course, CoursePlan> coursePlan;
	
	/**
	 * @see com.online.shop.entity.Course#price
	 **/
	public static volatile SingularAttribute<Course, BigDecimal> price;
	
	/**
	 * @see com.online.shop.entity.Course#description
	 **/
	public static volatile SingularAttribute<Course, String> description;
	
	/**
	 * @see com.online.shop.entity.Course#id
	 **/
	public static volatile SingularAttribute<Course, Long> id;
	
	/**
	 * @see com.online.shop.entity.Course#categories
	 **/
	public static volatile ListAttribute<Course, Category> categories;
	
	/**
	 * @see com.online.shop.entity.Course#title
	 **/
	public static volatile SingularAttribute<Course, String> title;
	
	/**
	 * @see com.online.shop.entity.Course
	 **/
	public static volatile EntityType<Course> class_;

}

