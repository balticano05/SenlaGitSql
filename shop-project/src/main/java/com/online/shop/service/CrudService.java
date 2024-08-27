package com.online.shop.service;

import org.modelmapper.ModelMapper;

public abstract class CrudService<T> implements GeneralService<T>{
    protected ModelMapper modelMapper;

    public CrudService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
