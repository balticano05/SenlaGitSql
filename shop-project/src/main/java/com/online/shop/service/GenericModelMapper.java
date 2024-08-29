package com.online.shop.service;

import org.modelmapper.ModelMapper;

public abstract class GenericModelMapper {

    protected ModelMapper modelMapper;

    public GenericModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
