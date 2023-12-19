package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 등록
    public void save(Item item){
        if(item.getId() == null) { // JPA 에 저장하기 전까지 ID가 없다.(즉 새로 생성한 객체 라는 뜻)
            em.persist(item);
        }else {                    // ID가 있다는 뜻은 (즉 이미 DB에 등록된 어디선가 가져온것)
            em.merge(item); // UPDATE 라고 일단 생각
        }
    }

    // 상품 단건 조회
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    // 상품 전체 조회 (JPQL 사용)
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }


}
