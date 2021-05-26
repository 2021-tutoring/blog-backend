package com.alwayslearn.blog.post;


import com.alwayslearn.blog.common.BaseControllerTest;
import com.alwayslearn.blog.contorller.request.UpdatePostRequest;
import com.alwayslearn.blog.model.dto.ModifyPostDto;
import com.alwayslearn.blog.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("댓글 수정 테스트")
public class UpdatePostTest extends BaseControllerTest {

    @Autowired
    PostService postService;

    @Test
    @DisplayName("댓글 수정(성공)")
    void updateCommentSuccess() throws Exception {
        //Given
        Long postId = postService.writePost( new ModifyPostDto(1, "제목", "내용")).getPostId();
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(1, "제목", "내용");

        //When
        ResultActions resultActions = this.mockMvc.perform(put("/posts/{postId}", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatePostRequest))
        );
        //Then
        resultActions.andExpect(status().isOk())

                .andExpect(jsonPath("userId").value(1))
                .andExpect(jsonPath("title").value("제목"))
                .andExpect(jsonPath("subject").value("내용"))

                .andDo(document("update-post",
                        requestFields(
                                fieldWithPath("subject").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목")
                        ),
                        responseFields(
                                fieldWithPath("postId").type(JsonFieldType.NUMBER).description("포스트 ID"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("포스트 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("subject").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("createdDate").type(JsonFieldType.STRING).description("포스트 ID"),
                                fieldWithPath("commentNum").type(JsonFieldType.NUMBER).description("포스트 ID"),
                                fieldWithPath("viewCount").type(JsonFieldType.NUMBER).description("포스트 ID")
                        )
                ))
        ;

    }
}