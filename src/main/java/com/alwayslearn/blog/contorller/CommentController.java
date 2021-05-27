package com.alwayslearn.blog.contorller;

import com.alwayslearn.blog.contorller.request.AddCommentRequest;
import com.alwayslearn.blog.contorller.request.UpdateCommentRequest;
import com.alwayslearn.blog.contorller.response.CommentResponse;
import com.alwayslearn.blog.contorller.response.CommentsResponse;
import com.alwayslearn.blog.model.dto.CommentDto;
import com.alwayslearn.blog.model.dto.ModifyCommentDto;
import com.alwayslearn.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentsResponse getComments(@RequestParam(required = false) Long size, @RequestParam(required = false) Long page, @PathVariable long postId) {
        List<CommentDto> comment = this.commentService.getComment(size, page, postId);
        return new CommentsResponse(comment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentResponse addComment(@PathVariable long postId, @RequestBody AddCommentRequest addCommentRequest) {
        CommentDto comment = commentService.addComment(postId, new ModifyCommentDto(addCommentRequest));
        return new CommentResponse(comment);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponse updateComment(@PathVariable String postId, @PathVariable long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        CommentDto comment = commentService.updateComment(commentId, updateCommentRequest.getContent());
        return new CommentResponse(comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        commentService.deleteComment(commentId);
    }
}
