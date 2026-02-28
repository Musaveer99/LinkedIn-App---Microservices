package com.posts_service.service;


import com.posts_service.dto.PostCreateRequestDto;
import com.posts_service.dto.PostDto;
import com.posts_service.entity.Post;
import com.posts_service.exception.ResourceNotFoundException;
import com.posts_service.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service

@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    public PostDto createPost(PostCreateRequestDto postDto, Long userId) {
        Post post = modelMapper.map(postDto, Post.class);

        post.setUserId(userId);

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        Post post= postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post not found with id:"+ postId));
        return modelMapper.map(post, PostDto.class);


    }
}
