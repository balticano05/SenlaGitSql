package com.online.shop.service;

import com.online.shop.dto.RoleDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RoleService {
    Long insert(RoleDto entityDto);

    RoleDto update(Long id, RoleDto entityDto);

    RoleDto findById(Long id);

    List<RoleDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);
}