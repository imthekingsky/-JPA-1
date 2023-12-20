package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private Long id;
    private String name;
    private Address address;
}
