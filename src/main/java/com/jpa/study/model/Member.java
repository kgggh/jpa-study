package com.jpa.study.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        if(image == null) {
            return;
        }
        image.setMember(this);
        this.images.add(image);
    }

    public void update(String name, List<Image> images) {
        this.name = name;
        this.images = images;
    }
}
