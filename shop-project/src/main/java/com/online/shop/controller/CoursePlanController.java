package com.online.shop.controller;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.service.CoursePlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course-plans")
public class CoursePlanController {

    private final CoursePlanService coursePlanService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Long insert(@Valid @RequestBody CoursePlanDto coursePlanDto) {
        log.info("Executing insert method in CoursePlanController with JSON processing");
        return coursePlanService.insert(coursePlanDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public CoursePlanDto update(@PathVariable Long id, @Valid @RequestBody CoursePlanDto coursePlanDto) {
        log.info("Executing update method in CoursePlanController with JSON processing");
        return coursePlanService.update(id, coursePlanDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in CoursePlanController with JSON processing");
        return coursePlanService.delete(id);
    }

    @GetMapping
    public List<CoursePlanDto> getAll() {
        log.info("Executing getAll method in CoursePlanController with JSON processing");
        return coursePlanService.getAll();
    }

    @GetMapping("/{id}")
    public CoursePlanDto getById(@PathVariable Long id) {
        log.info("Executing getById method in CoursePlanController with JSON processing");
        return coursePlanService.findById(id);
    }

}