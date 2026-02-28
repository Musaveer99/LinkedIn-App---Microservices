package com.posts_service.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.posts_service.entity.PostLike;
import com.posts_service.exception.BadRequestException;
import com.posts_service.exception.ResourceNotFoundException;
import com.posts_service.repository.PostLikeRepository;
import com.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private static final Logger log =
            LoggerFactory.getLogger(PostLikeService.class);

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostLikeRepository postLikeRepository;

    public void likePost(Long postId, Long userId){
        log.info("Attempting to like a post with id: {}", postId);
            boolean exists = postRepository.existsById(postId);

            if(!exists) throw new ResourceNotFoundException("Post not found: "+ postId);

            boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(postId, userId);
            if(alreadyLiked) throw new BadRequestException("Cannot like the post again.");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);
        log.info("Post with id: {} liked successfully", postId);
    }

    @Transactional
    public void UnLikePost(Long postId, long userId) {
        log.info("Attempting to unlike a post with id: {}", postId);
        boolean exists = postRepository.existsById(postId);

        if(!exists) throw new ResourceNotFoundException("Post not found: "+ postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(postId, userId);
        if(!alreadyLiked) throw new BadRequestException("Cannot unlike the post which is not liked.");

        postLikeRepository.deleteByUserIdAndPostId(postId, userId);
        log.info("Post with id: {} unliked successfully", postId);
    }
}
