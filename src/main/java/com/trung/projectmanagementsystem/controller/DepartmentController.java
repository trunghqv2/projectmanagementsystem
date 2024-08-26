package com.trung.projectmanagementsystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trung.projectmanagementsystem.model.form.department.DepartmentFilterForm;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping(value = "appi/v1/departments")
@Validated
@Log4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Page<DepartmentDTO> getAllDepartments(Pageable pageable, @Valid DepartmentFilterForm form) {
        return departmentService.getAllDepartments(pageable, form);
    }
}
