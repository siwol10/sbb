package com.mysite.sbb.question;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;

	/*
	 * @GetMapping("/list") public String list(Model model) { List<Question>
	 * questionList = this.questionService.getList();
	 * model.addAttribute("questionList", questionList); return "question_list"; }
	 */
	
	//페이징을 위해 list 메서드 변경
	//GET 방식으로 요청된 URL에서 page값을 가져오기 위해 list 메서드의 매개변수 추가
	//URL에 매개변수로 page가 전달되지 않은 경우 기본값은 0이 되도록 설정했다.
	@GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
    
    //숫자 2처럼 변하는 id값을 얻을 때에는 @PathVariable 애너테이션을 사용
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id,AnswerForm answerForm) {
    	Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
    	return "question_detail";
    }
    
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    
    //POST 방식으로 요청한 /question/create URL을 처리하도록 
    //@PostMapping 애너테이션을 지정한 questionCreate 메서드를 추가
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        //questionCreate 메서드는 bindResult.hasErrors()를 호출하여 오류가 있는 경우에는 다시 제목과 내용을 작성하는 화면으로 돌아가도록 했고
    	//오류가 없을 경우에만 질문이 등록
    	if (bindingResult.hasErrors()) {
            return "question_form";
        }
    	this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
    
}
