package com.alwayslearn.blog.post;

import com.alwayslearn.blog.common.BaseControllerTest;
import com.alwayslearn.blog.model.dto.ModifyPostDto;
import com.alwayslearn.blog.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시물 조회 테스트")
public class GetPostsTest extends BaseControllerTest {

    @Autowired
    PostService postService;

    @Test
    @DisplayName("게시물 조회 (성공)")
    void GetPostSuccess() throws Exception {
        //Given
        postService.writePost((long) 1,new ModifyPostDto(1,"제목","내용")).getPostId();
        postService.writePost((long) 1,new ModifyPostDto(1,"제목","내용")).getPostId();
        //When
        ResultActions resultActions = this.mockMvc.perform(get("/boards/{boardId}/posts",1));

        //Then
        resultActions.andExpect(status().isOk())

                .andExpect(jsonPath("posts[0].userId").value(1))
                .andExpect(jsonPath("posts[0].title").value("제목"))
                .andExpect(jsonPath("posts[0].subject").value("내용"))

                .andDo(document("get-posts",
                        responseFields(
                                fieldWithPath("posts[].postId").type(JsonFieldType.NUMBER).description("보드 ID"),
                                fieldWithPath("posts[].userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                fieldWithPath("posts[].title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("posts[].subject").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("posts[].createdDate").type(JsonFieldType.STRING).description("날짜"),
                                fieldWithPath("posts[].commentNum").type(JsonFieldType.NUMBER).description("댓글 수"),
                                fieldWithPath("posts[].viewCount").type(JsonFieldType.NUMBER).description("조회 수"))
                        )
                )
        ;
    }

}
