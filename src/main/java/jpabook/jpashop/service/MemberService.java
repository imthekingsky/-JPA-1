package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA의 모든 데이터 변경은 트랜잭션 안에서 이루어 져야 한다.(Spring 이 제공하는 @Transactional 사용)
@RequiredArgsConstructor // 롬복 사용 (회원 서비스 개발 15분 10초)
public class MemberService {

//    @Autowired // 스프링이 스프링빈에 등록되어있는 memberRepository 인젝션 (필드 인젝션)
    private final MemberRepository memberRepository; // 상수는 초기화된 후에 변경할 수 없음

//    @Autowired // 생성자 인젝션 // 장점: 테스트 코드 작성시 몫(리포지토리)같은것을 주입 가능, Setter 단점 개선
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //    @Autowired // Setter 인젝션 // 장점: 테스트 코드 작성시 몫(리포지토리)같은것을 주입 가능 단점: 뭔가있음 암튼
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional(readOnly = false) // 쓰기,읽기 OK
    public Long join(Member member){

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); // 동시에 Insert 통과 할수도 있기때문에 실무에서는 최후의 방어: DB에 MEMBER 의 NAME을 유니크 제약조건을 걸어주자
        if(findMembers.size() > 0){
            throw new IllegalStateException("이미 존재하는 회원입니다."); //EXCEPTION
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }



}
