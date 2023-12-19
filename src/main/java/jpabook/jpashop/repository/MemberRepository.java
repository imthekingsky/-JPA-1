package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // @Component 스캔에 의해서 자동으로 빈에 등록
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // 스프링이 em 생성해서 인젝션해준다
    private final EntityManager em; // 상수는 초기화된 후에 변경할 수 없음

    // 저장
    public void save(Member member) {
        em.persist(member);
    }

    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 전체 조회(리스트) // JPQL 이용
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // 단축키 Ctrl + Alt + N
    }

    // 이름으로 조회 // JPQL 이용 (파라미터 바인딩)
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
