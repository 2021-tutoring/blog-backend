package com.alwayslearn.blog.service;

import com.alwayslearn.blog.exception.PostCantUpdateException;
import com.alwayslearn.blog.exception.PostNotFoundException;
import com.alwayslearn.blog.model.Post;
import com.alwayslearn.blog.model.dto.ModifyPostDto;
import com.alwayslearn.blog.model.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        post.increaseViewCount();
        return post;
    }

    public Post writePost(ModifyPostDto modifyPostDto) {
        Post post = new Post(modifyPostDto);
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, ModifyPostDto modifyPostDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostCantUpdateException(postId));
        post.editPost(modifyPostDto.getTitle(), modifyPostDto.getSubject());
        return postRepository.save(post);

    }

    public Page<Post> getPosts(
            Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }
}
