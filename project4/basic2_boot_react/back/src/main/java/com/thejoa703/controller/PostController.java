package com.thejoa703.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.entity.Post;
import com.thejoa703.oauth2.CustomOAuth2User;
import com.thejoa703.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(
            @AuthenticationPrincipal CustomOAuth2User userPrincipal,
            @RequestPart("content") String content,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        Post post = postService.createPost(userPrincipal.getId(), content, file);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @AuthenticationPrincipal CustomOAuth2User userPrincipal,
            @PathVariable Long postId) {
        
        postService.deletePost(postId, userPrincipal.getId());
        return ResponseEntity.noContent().build();
    }
}