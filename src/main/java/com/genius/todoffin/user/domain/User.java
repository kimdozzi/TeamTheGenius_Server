package com.genius.todoffin.user.domain;

import com.genius.todoffin.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String provider;
    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(unique = true, length = 16)
    private String nickname;

    @Column(length = 160)
    private String information;
    private String interest;


    @Builder
    public User(String provider, String email, Role role, String nickname, String information,
                String interest) {
        this.provider = provider;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
        this.information = information;
        this.interest = interest;
    }

    public User() {
    }
}
