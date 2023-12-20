package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // Form 회원가입 화면(GET)
    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    // 회원 등록(POST)
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) { // @Valid 사용한 객체안에 @NotEmpty 등.. 검증을 해준다.
                                                                         // 오류가 BidingResult 에 담기고 스프링이 에러를 view 단으로 끌고 가준다
        if(result.hasErrors()){ // 회원 이름 작성 필수 검증
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        try {
            memberService.join(member);
        }catch (IllegalStateException e){ // 회원 이름 중복 검증
            result.addError(new FieldError("memberForm", "name", e.getMessage()));
            return "members/createMemberForm";
        }
        return "redirect:/"; // "/" 는 home 으로 보낸다.
    }



}
