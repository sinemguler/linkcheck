package com.linkcheck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;


@Data
@Entity
@Component
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private  long id;

    private String name;

    private String surname;

    @NotNull
    private String email;

    private String password;
}
