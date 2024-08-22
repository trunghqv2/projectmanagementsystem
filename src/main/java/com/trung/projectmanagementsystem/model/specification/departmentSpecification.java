package com.trung.projectmanagementsystem.model.specification;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import org.apache.catalina.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;

import com.trung.projectmanagementsystem.model.entity.Department;
import com.trung.projectmanagementsystem.model.form.department.DepartmentFilterForm;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

public class departmentSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<Department>buildWhere(DepartmentFilterForm form){
        Specification<Department> where =null;

        if(form == null)return where;
        if(form.getQ() != null && !StringUtil.isEmpty(form.getQ().trim())){
            String sreach = form.getQ().trim();
            CustomSpecification departmentNameSpecification new CustomSpecification ("department",sreach);
            CustomSpecification managerFullameSpecification new CustomSpecification ("managerFullname",sreach); 
            CustomSpecification managerEmailSpecification new CustomSpecification ("managerEmail",sreach);
            CustomSpecification managerUsernameSpecification new CustomSpecification ("managerUsername",sreach); 
           
            where = Specification 
                    .where(departmentNameSpecification)
                    .or(managerFullameSpecification)
                    .or(managerEmailSpecification)
                    .or(managerUsernameSpecification);
        }
        if(form.getMinCreatedDate()!= null) {
            CustomSpecification minCreatedDateSpecification = new CustomSpecification ("minCreatedDate", form.getMinCreatedDate());
            if(where == null){
                where = minCreatedDateSpecification;
            }else {
                where = where.and(minCreatedDateSpecification);
            }
        }
        if(form.getMaxCreatedDate()!= null) {
            CustomSpecification maxCreatedDateSpecification = new CustomSpecification ("maxCreatedDate", form.getMaxCreatedDate());
            if(where == null){
                where = maxCreatedDateSpecification;
            }else {
                where = where.and(maxCreatedDateSpecification);
            }
        }
        if(form.getMinMemberSize()!= null) {
            CustomSpecification getMinMemberSizeSpecifiaction = new CustomSpecification ("getMinMemberSize", form.getMinMemberSize());
            if(where == null){
                where = getMinMemberSizeSpecifiaction;
            }else {
                where = where.and(getMinMemberSizeSpecifiaction);
            }
        }
        if(form.getMaxMemberSize()!= null) {
            CustomSpecification maxMemberSizeSpecification = new CustomSpecification ("maxMemberSize", form.getMaxMemberSize());
            if(where == null){
                where = maxMemberSizeSpecification;
            }else {
                where = where.and(maxMemberSizeSpecification);
            }
        }
        return where;
    }

    @SuppressWarnings("serial")
    @RequiredArgsConstructor
    static class CustomSpecification implements Specification<Department> {
        @NotNull
        private String fidel;

        @NotNull
        private Object value;

        @Override
        public Predicate toPredicate(
                Root<Department> root,
                CriteriaQuery<?> query,
                CriteriaBuilder criteriaBuilder) {

            if (Field.equalsIgnoreCase("departmentName")) {
                return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
            }

            if (Field.equalsIgnoreCase("managerFullname")) {
                return criteriaBuilder.like(root.get("manager").get("fullname"), "%" + value.toString() + "%");
            }

            if (Field.equalsIgnoreCase("managerEmail")) {
                return criteriaBuilder.like(root.get("manager").get("email"), "%" + value.toString() + "%");
            }

            if (Field.equalsIgnoreCase("managerUsername")) {
                return criteriaBuilder.like(root.get("manager").get("username"), "%" + value.toString() + "%");
            }

            if (Field.equalsIgnoreCase("minCreatedDate")) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get("createdDateTime").as(java.sql.Date.class),
                        (Date) value);
            }

            if (Field.equalsIgnoreCase("maxCreatedDate")) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get("createdDateTime").as(java.sql.Date.class),
                        (Date) value);
            }
            if (Field.equalsIgnoreCase("minMemberSize")) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get("memberSize"), value.toString());
            }
            if (Field.equalsIgnoreCase("maxMemberSize")) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get("memberSize"), value.toString());
            }
            return null;
        }

    }
}
