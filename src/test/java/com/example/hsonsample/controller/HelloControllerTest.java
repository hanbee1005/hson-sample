package com.example.hsonsample.controller;

import com.example.hsonsample.common.CommonControllerTest;
import com.example.hsonsample.util.RestdocsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest extends CommonControllerTest {

    @Test
    void hello() throws Exception {
        // given
        String documentPath = "hello";
        String url = API_V1 + "/";

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("hello").type(JsonFieldType.STRING).description("인사")
                        )))
                .andDo(print());
    }
}