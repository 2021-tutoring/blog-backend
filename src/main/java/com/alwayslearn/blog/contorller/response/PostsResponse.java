package com.alwayslearn.blog.contorller.response;

import com.alwayslearn.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostsResponse {
    private List<Post> posts;

}
