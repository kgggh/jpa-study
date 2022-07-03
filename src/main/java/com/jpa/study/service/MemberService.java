package com.jpa.study.service;

import com.jpa.study.repository.ImageRepo;
import com.jpa.study.model.Member;
import com.jpa.study.model.MemberDTO;
import com.jpa.study.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepo memberRepo;
    private final ImageRepo imageRepo;

    @Transactional
    public Long create(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        memberDTO.getImages().forEach(i-> {
            imageRepo.save(i);
            member.addImage(i);
        });
        Member result = memberRepo.save(member);
        return result.getId();
    }

    @Transactional
    public void update(Long memberId, MemberDTO memberDTO) {
        log.debug("{}, {}",memberId, memberDTO);
        Member member = memberRepo.findById(memberId).get();
        member.update(memberDTO.getName(), memberDTO.getImages());
    }

    public List<Member> get(){
        return memberRepo.findAll();
    }

    public Member get(Long memberId){
        return memberRepo.findById(memberId).get();
    }
}
