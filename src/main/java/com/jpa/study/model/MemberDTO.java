package com.jpa.study.model;

import lombok.Data;

import java.util.List;

@Data
public class MemberDTO {
    private List<Image> images;
    private String name;
}
