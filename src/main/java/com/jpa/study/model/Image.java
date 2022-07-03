package com.jpa.study.model;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;


    public void setMember(Member member) {
        this.member = member;
    }
}
