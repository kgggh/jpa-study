package com.jpa.study.controller;

import com.jpa.study.model.Member;
import com.jpa.study.model.MemberDTO;
import com.jpa.study.model.MemberResponseDTO;
import com.jpa.study.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/members")
    public Long createMember(@RequestBody MemberDTO memberDTO) {
        Long postId = memberService.create(memberDTO);
        return postId;
    }

    @PatchMapping("/api/members/{member_id}")
    public ResponseEntity<Object> updateMember(@PathVariable("member_id") Long memberId, @RequestBody MemberDTO memberDTO) {
        log.debug("{}", memberId);
        memberService.update(memberId,memberDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/members")
    public List<MemberResponseDTO> get(){
        List<Member> members = memberService.get();
        List<MemberResponseDTO> memberResponseDTO = members.stream()
                .map(MemberResponseDTO::new)
                .collect(Collectors.toList());
        return memberResponseDTO;
    }

    @GetMapping("/api/members/{member_id}")
    public MemberResponseDTO get(@PathVariable("member_id") Long memberId){
        log.debug("{}", memberId);
        Member member = memberService.get(memberId);
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setImages(member.getImages());
        memberResponseDTO.setName(member.getName());
        return memberResponseDTO;
    }
}
