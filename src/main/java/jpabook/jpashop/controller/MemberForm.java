package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

//  사용하려면 의존성 추가 implementation 'org.springframework.boot:spring-boot-starter-validation'
//  스프링이 값이 비어 있으면 오류
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
