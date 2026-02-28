package com.posts_service.controller;

import com.posts_service.dto.PostCreateRequestDto;
import com.posts_service.dto.PostDto;
import com.posts_service.entity.Post;
import com.posts_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
     PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostCreateRequestDto postDto, HttpServletRequest request) {

        PostDto createdPost = postService.createPost(postDto, 1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable Long postId) {

        PostDto  postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostDto> posts = postService.getAllPostsOfUser(userId);
                return new ResponseEntity<>(posts, HttpStatus.OK);



    }



}
