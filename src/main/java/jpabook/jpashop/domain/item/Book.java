package jpabook.jpashop.domain.item;

import jpabook.jpashop.service.UpdateBookDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item{
    private String author;
    private String isbn;

    //==비즈니스 메서드==//
    public void changeBook(UpdateBookDto updateBookDto) {

        this.setName(updateBookDto.getName());
        this.setPrice(updateBookDto.getPrice());
        this.setStockQuantity(updateBookDto.getStockQuantity());
        this.setAuthor(updateBookDto.getAuthor());
        this.setIsbn(updateBookDto.getIsbn());
    }
}
