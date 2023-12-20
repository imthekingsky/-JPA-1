package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 PROTECTED 설정
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량


    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) { // 쿠폰 등.. 떄문에 orderPrice 를 따로 받는다
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 주문 수량만큼 상품에서 재고 감소
        return orderItem;
    }


    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(getCount()); // 주문 수량만큼 상품에 원복(변수 대신 getter 사용 프록시때문에)
    }

    //==조회 로직==//

    /**
     * 주문 상품 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
