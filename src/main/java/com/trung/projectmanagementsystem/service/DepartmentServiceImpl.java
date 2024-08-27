package com.trung.projectmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.trung.projectmanagementsystem.model.dto.department.DepartmentDTO;
import com.trung.projectmanagementsystem.model.entity.Department;
import com.trung.projectmanagementsystem.model.form.department.DepartmentFilterForm;
import com.trung.projectmanagementsystem.model.specification.departmentSpecification;
import com.trung.projectmanagementsystem.reponository.IDepartmentRepository;

public class DepartmentServiceImpl extends BaseService implements DepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public Page<DepartmentDTO> getAllDepartments(Pageable pageable, DepartmentFilterForm form) {
        
        Specification<Department> where = departmentSpecification.buildWhere(form);
        
        Page<Department> entityPage = departmentRepository.findAll(where, pageable);
        
        Page<DepartmentDTO> dtoPage = convertObjectPageToObjectPage(entityPage, pageable, DepartmentDTO.class);
        
        return dtoPage;
    }

}
