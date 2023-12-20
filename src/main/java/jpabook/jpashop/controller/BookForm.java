package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    // 상품 등록시 Id 필요
    private Long id;

    // 공통 속성
    @NotEmpty(message = "상품 이름은 필수 입니다.")
    private String name;
    private int price;
    private int stockQuantity;

    // 개별 속성
    private String author;
    private String isbn;

}
