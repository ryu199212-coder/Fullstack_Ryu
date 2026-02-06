package com.thejoa703.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.entity.AppUser;
import com.thejoa703.entity.Post;
import com.thejoa703.repository.AppUserRepository;
import com.thejoa703.repository.PostRepository;
import com.thejoa703.util.FileStorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;
    private final FileStorageService fileStorageService;

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시글 작성
    public Post createPost(Long userId, String content, MultipartFile file) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        String filePath = null;
        if (file != null && !file.isEmpty()) {
            filePath = fileStorageService.storeFile(file); // FileStorageService 메서드명 확인
        }

        Post post = Post.builder()
                .user(user)
                .content(content)
                .pfile(filePath)
                .build();

        return postRepository.save(post);
    }

    // 게시글 삭제
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        
        // 작성자 본인 확인
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }
        
        postRepository.delete(post);
    }
}