package com.linkedIn.posts_service.controller;

import com.linkedIn.posts_service.dto.PostCreateRequestDto;
import com.linkedIn.posts_service.dto.PostDto;
import com.linkedIn.posts_service.service.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    public ResponseEntity<PostDto>  createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
        PostDto createdPost = postService.createPost(postCreateRequestDto, 1L);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

    }

}
