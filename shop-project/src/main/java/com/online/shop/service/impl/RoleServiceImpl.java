package com.online.shop.service.impl;

import com.online.shop.service.RoleService;
import com.online.shop.dto.RoleDto;
import com.online.shop.entity.Role;
import com.online.shop.repository.RoleDao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(RoleDto entityDto) {
        log.info("Executing insert method in RoleServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("RoleDto is null in insert method");
            throw new IllegalArgumentException("RoleDto cannot be null");
        }
        return roleDao.insert(modelMapper.map(entityDto, Role.class));
    }

    @Override
    public RoleDto update(Long id, RoleDto entityDto) {
        log.info("Executing update method in RoleServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("RoleDto is null in update method");
            throw new IllegalArgumentException("RoleDto cannot be null");
        }
        Role updatedRole = roleDao.update(id, modelMapper.map(entityDto, Role.class))
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return modelMapper.map(updatedRole, RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) {
        log.info("Executing findById method in RoleServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Role foundRole = roleDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return modelMapper.map(foundRole, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll(PageRequest pageRequest) {
        log.info("Executing getAll method in RoleServiceImpl");
        return roleDao.getAll(pageRequest).stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Executing delete method in RoleServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return roleDao.delete(id);
    }

}