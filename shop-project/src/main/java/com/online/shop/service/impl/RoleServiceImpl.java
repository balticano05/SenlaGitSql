package com.online.shop.service.impl;

import com.online.shop.service.RoleService;
import com.online.shop.dto.RoleDto;
import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

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
        log.info(LOG_EXECUTING_INSERT_METHOD);
        return roleDao.insert(modelMapper.map(entityDto, Role.class));
    }

    @Override
    public RoleDto update(Long id, RoleDto entityDto) {
        log.info(LOG_EXECUTING_UPDATE_METHOD);
        return modelMapper.map(roleDao.update(id, modelMapper.map(entityDto, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) {
        log.info(LOG_EXECUTING_FIND_BY_ID_METHOD);
        return modelMapper.map(roleDao.getById(id), RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return roleDao.getAll().stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD);
        return roleDao.delete(id);
    }

}