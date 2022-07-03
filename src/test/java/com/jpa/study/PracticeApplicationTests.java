package com.jpa.study;

import com.jpa.study.model.Image;
import com.jpa.study.repository.ImageRepo;
import com.jpa.study.model.Member;
import com.jpa.study.repository.MemberRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class PracticeApplicationTests {
	@Autowired MemberRepo memberRepo;
	@Autowired ImageRepo imageRepo;

	@BeforeEach
	void init() {
		Member memberA = new Member();
		memberA.setName("하이");

		memberRepo.save(memberA);

		Image imageA = new Image();
		imageA.setImageName("하이");

		Image imageB = new Image();
		imageB.setImageName("하이");

		imageRepo.save(imageA);
		imageRepo.save(imageB);
		memberA.addImage(imageA);
		memberA.addImage(imageB);
	}

	@Test
	void contextLoads() {
		Member member = memberRepo.findById(1L).get();
		System.out.println(member);

		member.getImages().forEach(i-> System.out.println(i.getMember().getName()));

	}
}
