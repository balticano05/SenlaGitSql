package com.online.shop.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Role_ {

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

	
	/**
	 * @see com.online.shop.entity.Role#name
	 **/
	public static volatile SingularAttribute<Role, String> name;
	
	/**
	 * @see com.online.shop.entity.Role#description
	 **/
	public static volatile SingularAttribute<Role, String> description;
	
	/**
	 * @see com.online.shop.entity.Role#id
	 **/
	public static volatile SingularAttribute<Role, Long> id;
	
	/**
	 * @see com.online.shop.entity.Role
	 **/
	public static volatile EntityType<Role> class_;

}

