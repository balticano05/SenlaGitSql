package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CoursePlan.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CoursePlan_ {

	public static final String LESSON_COUNT = "lessonCount";
	public static final String DURATION = "duration";
	public static final String PRACTICE_COUNT = "practiceCount";
	public static final String COURSE = "course";
	public static final String ID = "id";

	
	/**
	 * @see com.online.shop.entity.CoursePlan#lessonCount
	 **/
	public static volatile SingularAttribute<CoursePlan, Integer> lessonCount;
	
	/**
	 * @see com.online.shop.entity.CoursePlan#duration
	 **/
	public static volatile SingularAttribute<CoursePlan, Integer> duration;
	
	/**
	 * @see com.online.shop.entity.CoursePlan#practiceCount
	 **/
	public static volatile SingularAttribute<CoursePlan, Integer> practiceCount;
	
	/**
	 * @see com.online.shop.entity.CoursePlan#course
	 **/
	public static volatile SingularAttribute<CoursePlan, Course> course;
	
	/**
	 * @see com.online.shop.entity.CoursePlan#id
	 **/
	public static volatile SingularAttribute<CoursePlan, Long> id;
	
	/**
	 * @see com.online.shop.entity.CoursePlan
	 **/
	public static volatile EntityType<CoursePlan> class_;

}

