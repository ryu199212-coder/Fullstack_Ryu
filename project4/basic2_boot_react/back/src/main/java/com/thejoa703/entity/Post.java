package com.thejoa703.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "POST")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder // 서비스에서 Post.builder()를 사용하기 위해 필수
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "POST_SEQ", allocationSize = 1)
    private Long id;

    @Column(length = 1000)
    private String content; // PostService의 .content()와 매칭됨

    private String pfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser user;

    // ✅ Hashtag 엔티티의 mappedBy = "hashtags"와 연결되는 주인 필드
    @ManyToMany
    @JoinTable(
        name = "post_hashtag", // 중간 테이블 이름
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    @Builder.Default // 빌더 사용 시 리스트가 null이 되지 않도록 방어
    private List<Hashtag> hashtags = new ArrayList<>();

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}