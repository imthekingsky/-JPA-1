package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링 부트가 엔티티메니저를 주입해 준다 (spring-boot-starter-data-jpa)
    private EntityManager em;

    // 저장
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 조회
    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
