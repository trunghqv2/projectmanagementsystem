package com.trung.projectmanagementsystem.reponository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trung.projectmanagementsystem.model.entity.Group;

public interface IGroupRepository extends JpaRepository<Group, Integer>  {
    
}
