package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입 사용(값 타입?)
    private Address address;

    @OneToMany(mappedBy = "member") // 연관관계 주인 설정
    private List<Order> orders = new ArrayList<>();

}
