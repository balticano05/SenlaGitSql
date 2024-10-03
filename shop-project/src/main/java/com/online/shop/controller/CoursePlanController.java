package com.online.shop.controller;

import com.online.shop.dto.CoursePlanDto;
import com.online.shop.service.CoursePlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/course-plans")
public class CoursePlanController {

    private final CoursePlanService coursePlanService;

    @Autowired
    public CoursePlanController(CoursePlanService coursePlanService) {
        this.coursePlanService = coursePlanService;
    }

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody CoursePlanDto coursePlanDto) {
        log.info("Executing insert method in CoursePlanController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(coursePlanService.insert(coursePlanDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursePlanDto> update(@PathVariable Long id, @RequestBody CoursePlanDto coursePlanDto) {
        log.info("Executing update method in CoursePlanController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(coursePlanService.update(id, coursePlanDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in CoursePlanController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(coursePlanService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<CoursePlanDto>> getAll() {
        log.info("Executing getAll method in CoursePlanController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(coursePlanService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursePlanDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in CoursePlanController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(coursePlanService.findById(id));
    }
}