package com.trung.projectmanagementsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.trung.projectmanagementsystem.model.dto.department.DepartmentDTO;
import com.trung.projectmanagementsystem.model.form.department.DepartmentFilterForm;

public interface DepartmentService {
    Page<DepartmentDTO> getAllDepartments(Pageable pageable, DepartmentFilterForm form);
}
