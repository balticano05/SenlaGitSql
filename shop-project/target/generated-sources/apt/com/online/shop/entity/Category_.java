package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Category_ {

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

	
	/**
	 * @see com.online.shop.entity.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see com.online.shop.entity.Category#description
	 **/
	public static volatile SingularAttribute<Category, String> description;
	
	/**
	 * @see com.online.shop.entity.Category#id
	 **/
	public static volatile SingularAttribute<Category, Long> id;
	
	/**
	 * @see com.online.shop.entity.Category
	 **/
	public static volatile EntityType<Category> class_;

}

