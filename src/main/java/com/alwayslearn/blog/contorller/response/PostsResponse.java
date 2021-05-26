package com.alwayslearn.blog.contorller.response;

import com.alwayslearn.blog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PostsResponse {
    private Page<Post> posts;

}
