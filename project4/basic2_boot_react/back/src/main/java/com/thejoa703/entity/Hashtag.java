package com.thejoa703.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity    
@Table(name= "HASHTAGS")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hashtag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hashtag_seq")
    @SequenceGenerator(name = "hashtag_seq", sequenceName = "HASHTAG_SEQ", allocationSize = 1)
    private Long id; 
    
    @Column(length = 200, nullable = false, unique = true)
    private String name;

    // ✅ Post 엔티티에 선언한 필드명 "hashtags"를 mappedBy에 적어야 합니다.
    @ManyToMany(mappedBy = "hashtags")
    @Builder.Default
    private List<Post> posts = new ArrayList<>();   
}