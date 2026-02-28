package com.posts_service.repository;


import com.posts_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByUserIdAndPostId(Long postId, Long userId);

    void deleteByUserIdAndPostId(Long postId, long userId);
}
