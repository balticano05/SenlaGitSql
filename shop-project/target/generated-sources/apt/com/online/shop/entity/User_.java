package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(User.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class User_ {

	public static final String CREATED_AT = "createdAt";
	public static final String COURSES = "courses";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String ID = "id";
	public static final String EMAIL = "email";

	
	/**
	 * @see com.online.shop.entity.User#createdAt
	 **/
	public static volatile SingularAttribute<User, LocalDateTime> createdAt;
	
	/**
	 * @see com.online.shop.entity.User#courses
	 **/
	public static volatile ListAttribute<User, Course> courses;
	
	/**
	 * @see com.online.shop.entity.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see com.online.shop.entity.User#role
	 **/
	public static volatile SingularAttribute<User, Role> role;
	
	/**
	 * @see com.online.shop.entity.User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see com.online.shop.entity.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see com.online.shop.entity.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;

}

