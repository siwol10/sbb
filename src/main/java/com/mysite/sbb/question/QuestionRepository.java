package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository는 CRUD작업을 처리하는 메서드를 이미 내장하고 있다.
//Question 엔티티로 repository 생성
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//findBySubject를 기본 제공하지 않아 인터페이스 변경
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	//페이징
	Page<Question> findAll(Pageable pageable);
}
