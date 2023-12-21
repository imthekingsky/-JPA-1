package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateBookDto {

    private String name;
    private int price;
    private int stockQuantity;

    // 개별 속성
    private String author;
    private String isbn;

    public UpdateBookDto(String name, int price, int stockQuantity, String author, String isbn) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
}
