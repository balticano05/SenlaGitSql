package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@StaticMetamodel(Transaction.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Transaction_ {

	public static final String DATE_TIME = "dateTime";
	public static final String PRICE = "price";
	public static final String COURSE = "course";
	public static final String ID = "id";
	public static final String USER = "user";

	
	/**
	 * @see com.online.shop.entity.Transaction#dateTime
	 **/
	public static volatile SingularAttribute<Transaction, LocalDateTime> dateTime;
	
	/**
	 * @see com.online.shop.entity.Transaction#price
	 **/
	public static volatile SingularAttribute<Transaction, BigDecimal> price;
	
	/**
	 * @see com.online.shop.entity.Transaction#course
	 **/
	public static volatile SingularAttribute<Transaction, Course> course;
	
	/**
	 * @see com.online.shop.entity.Transaction#id
	 **/
	public static volatile SingularAttribute<Transaction, Long> id;
	
	/**
	 * @see com.online.shop.entity.Transaction
	 **/
	public static volatile EntityType<Transaction> class_;
	
	/**
	 * @see com.online.shop.entity.Transaction#user
	 **/
	public static volatile SingularAttribute<Transaction, User> user;

}

