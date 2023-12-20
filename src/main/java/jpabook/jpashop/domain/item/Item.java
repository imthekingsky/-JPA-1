package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnouhStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item { // 추상 클래스로 작성(구현체 사용함)(상속관계 매핑)

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    // 공통 속성들
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==// // 데이터를 가지고 있는 쪽에 비즈니스 메서드가 있는게 좋다
    // Setter 대신 비즈니스 메서드 이용
    // 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    // 재고 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) { // 남은 재고가 0개 이하면 예외 발생
            throw new NotEnouhStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }


}
