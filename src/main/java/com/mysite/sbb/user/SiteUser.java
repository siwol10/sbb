package com.mysite.sbb.user;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
public class SiteUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//유일 값 저장. 중복 막기
    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

}
