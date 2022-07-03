package com.jpa.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberResponseDTO {
    private List<ImageInfo> images = new ArrayList<>();
    private String name;

    public void setImages(List<Image> images) {
        System.out.println(images);
        if(CollectionUtils.isEmpty(images)) {
            return;
        }
        images.forEach(i-> {
            this.images.add(new ImageInfo(i.getId(), i.getImageName()));
        });
    }

    public MemberResponseDTO(Member member) {
        this.images = member.getImages().stream().map(ImageInfo::new).collect(Collectors.toList());
        this.name = member.getName();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageInfo {
        private Long id;
        private String imageName;

        public ImageInfo(Image image) {
            this.id = image.getId();
            this.imageName = image.getImageName();
        }
    }
}
