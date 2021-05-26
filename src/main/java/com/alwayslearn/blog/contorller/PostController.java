package com.alwayslearn.blog.contorller;

import com.alwayslearn.blog.contorller.request.UpdatePostRequest;
import com.alwayslearn.blog.contorller.request.WritePostRequest;
import com.alwayslearn.blog.contorller.response.PostResponse;
import com.alwayslearn.blog.contorller.response.PostsResponse;
import com.alwayslearn.blog.model.Post;
import com.alwayslearn.blog.model.dto.ModifyPostDto;
import com.alwayslearn.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostsResponse getPosts(){
        List<Post> posts = this.postService.getPosts();
        return new PostsResponse(posts);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPost(@PathVariable Long postId) {
        Post post = this.postService.getPost(postId);
        return new PostResponse(post);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse writePost(@RequestBody WritePostRequest writePostRequest) {
        Post post = postService.writePost(new ModifyPostDto(writePostRequest));
        return new PostResponse(post);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
        Post post = postService.updatePost(postId, new ModifyPostDto(updatePostRequest));
        return new PostResponse(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
    }
}
