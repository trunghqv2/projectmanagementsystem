package com.trung.projectmanagementsystem.model.form.account;

import org.hibernate.validator.constraints.Length;

import com.trung.projectmanagementsystem.model.validation.account.AccountEmailNotExists;
import com.trung.projectmanagementsystem.model.validation.account.AccountUsernameNotExists;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingAccountForm {
    @NotBlank
    @Length(max = 50)
    private String firstname;

    @NotBlank
    @Length(max = 50)
    private String lastname;

    @NotBlank
    @Length(min = 6, max = 50)
    @AccountUsernameNotExists
    private String username;

    @NotBlank
    @Email
    @AccountEmailNotExists
    private String email;

    @NotBlank
    @Length(min = 6 ,max = 50)
    private String password;
}