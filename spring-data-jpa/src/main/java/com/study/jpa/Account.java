package com.study.jpa;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity @SequenceGenerator(name="account_id_seq", sequenceName = "account_id_seq", initialValue = 1, allocationSize = 1)
public class Account {
    @Id
    @GeneratedValue(generator = "account_id_seq")
    private Long id;

    private String username;

    private String password;
}
