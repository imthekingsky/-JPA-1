package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class BookForm {

    // 상품 등록시 Id 필요
    private Long id;

    // 공통 속성
    @NotEmpty(message = "상품 이름은 필수 입니다.")
    @Pattern(regexp = "^(?!\\s+$).*$", message = "빈 공백은 불가능합니다.")
    private String name;
    private int price;
    private int stockQuantity;

    // 개별 속성
    private String author;
    private String isbn;

    // 상품명, 저자, ISBN 앞뒤 공백 제거
    public static void trimSpaces(BookForm bookForm) {

        // 앞뒤 공백 제거
        if (bookForm != null){
            bookForm.setName(bookForm.getName() != null ? bookForm.getName().trim() : null);
            bookForm.setAuthor(bookForm.getAuthor() != null ? bookForm.getAuthor().trim() : null);
            bookForm.setIsbn(bookForm.getIsbn() != null ? bookForm.getIsbn().trim() : null);
        }

    }

}
