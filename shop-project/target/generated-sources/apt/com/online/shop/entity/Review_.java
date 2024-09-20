package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Review.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Review_ {

	public static final String CREATED_AT = "createdAt";
	public static final String RATING = "rating";
	public static final String COURSE = "course";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String CONTENT = "content";

	
	/**
	 * @see com.online.shop.entity.Review#createdAt
	 **/
	public static volatile SingularAttribute<Review, LocalDateTime> createdAt;
	
	/**
	 * @see com.online.shop.entity.Review#rating
	 **/
	public static volatile SingularAttribute<Review, Integer> rating;
	
	/**
	 * @see com.online.shop.entity.Review#course
	 **/
	public static volatile SingularAttribute<Review, Course> course;
	
	/**
	 * @see com.online.shop.entity.Review#id
	 **/
	public static volatile SingularAttribute<Review, Long> id;
	
	/**
	 * @see com.online.shop.entity.Review
	 **/
	public static volatile EntityType<Review> class_;
	
	/**
	 * @see com.online.shop.entity.Review#user
	 **/
	public static volatile SingularAttribute<Review, User> user;
	
	/**
	 * @see com.online.shop.entity.Review#content
	 **/
	public static volatile SingularAttribute<Review, String> content;

}

