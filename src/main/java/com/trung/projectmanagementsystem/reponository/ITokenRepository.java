package com.trung.projectmanagementsystem.reponository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.trung.projectmanagementsystem.model.entity.Account;
import com.trung.projectmanagementsystem.model.entity.Token;
import com.trung.projectmanagementsystem.model.entity.Token.Type;
public interface ITokenRepository extends JpaRepository<Token, Integer> {
    @Modifying
    void deleteByTypeAndAccount(Type type, Account account);

    Token findBykeyAndType(String key, Type type);
}
