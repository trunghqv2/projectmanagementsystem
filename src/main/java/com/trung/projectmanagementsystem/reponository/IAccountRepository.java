package com.trung.projectmanagementsystem.reponository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trung.projectmanagementsystem.model.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Account findByUsername(String username);

    Account findByUsernameOrEmail(String username, String email);
}
