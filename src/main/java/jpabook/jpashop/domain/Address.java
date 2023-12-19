package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 값 타입은(값은 변경되면안된다) Setter 제공 X 생성자로 생성할때만
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // protected 설정
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
