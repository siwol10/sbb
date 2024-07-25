package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id	//id 속성을 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)	//자동으로 1씩 증가
    private Integer id;

    @Column(length = 200)	//열의 이름과 열의 세부 설정
    private String subject;

    @Column(columnDefinition = "TEXT")	//글자수 제한 x
    private String content;
    
    private LocalDateTime createDate;
    
    //질문 하나에 답변은 여러 개 이므로 answer 속성은 list형태로 구성
    @OneToMany(mappedBy="question", cascade = CascadeType.REMOVE)
    //answer 엔티티에서 question 엔티티를 참조한 속성인 "question"을 mappedBy에 전달
    private List<Answer> answerList;
}
