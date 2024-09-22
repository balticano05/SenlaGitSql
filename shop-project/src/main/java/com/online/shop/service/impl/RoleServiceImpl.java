package com.online.shop.service.impl;

import com.online.shop.service.RoleService;
import com.online.shop.dto.RoleDto;
import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;
    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, ModelMapper modelMapper) {
        this.roleDao = roleDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(RoleDto entityDto) {
        log.info("Executing insert method in RoleServiceImpl with DTO: {}", entityDto);
        return roleDao.insert(modelMapper.map(entityDto, Role.class));
    }

    @Override
    public RoleDto update(Long id, RoleDto entityDto) {
        log.info("Executing update method in RoleServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(roleDao.update(id, modelMapper.map(entityDto, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) {
        log.info("Executing findById method in RoleServiceImpl for ID: {}", id);
        return modelMapper.map(roleDao.getById(id), RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        log.info("Executing getAll method in RoleServiceImpl");
        return roleDao.getAll().stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in RoleServiceImpl for ID: {}", id);
        return roleDao.delete(id);
    }

}