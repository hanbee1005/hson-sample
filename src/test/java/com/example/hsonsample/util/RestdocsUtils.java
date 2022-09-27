package com.example.hsonsample.util;

import com.example.hsonsample.constant.common.HttpHeaderConstants;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class RestdocsUtils {
    private static final String SCHEME = "https";
    private static final String HOST = "gep.aipluslab.ai";
    private static final String SERVICE_NAME = "gig";

    public static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme(SCHEME)
                        .host(HOST)
                        .removePort(),
                prettyPrint());
    }

    public static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }

    public static ResponseFieldsSnippet commonResponseFields(FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                        fieldWithPath("successOrNot").type(JsonFieldType.STRING).description("요청 성공 여부"),
                        fieldWithPath("statusCode").type(JsonFieldType.STRING).description("결과 코드"))
                .and(descriptors);
    }

    public static ResponseFieldsSnippet commonResponseFields(boolean isCollection, FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                fieldWithPath("successOrNot").type(JsonFieldType.STRING).description("요청 성공 여부"),
                fieldWithPath("statusCode").type(JsonFieldType.STRING).description("결과 코드")).and(
                Arrays.stream(descriptors)
                        .map(fd -> {
                            if (fd.isOptional()) {
                                return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                            } else {
                                return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                            }
                        })
                        .collect(Collectors.toList()));
    }

    public static MockHttpServletRequestBuilder postWrapper(String url) {
        MockHttpServletRequestBuilder builder = post(url)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder postWrapper(String url, String requestString) {
        MockHttpServletRequestBuilder builder = post(url)
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder postWrapper(String url, String pathVariable, String requestString) {
        MockHttpServletRequestBuilder builder;
        if (StringUtils.hasText(requestString)) {
            builder = post(url, pathVariable)
                    .content(requestString)
                    .contentType(MediaType.APPLICATION_JSON);
        } else {
            builder = post(url, pathVariable)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder putWrapper(String url, String requestString) {
        MockHttpServletRequestBuilder builder = put(url)
                .content(requestString)
                .contentType(MediaType.APPLICATION_JSON);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder putWrapper(String url, String pathVariable, String requestString) {
        MockHttpServletRequestBuilder builder;
        if (StringUtils.hasText(requestString)) {
            builder = put(url, pathVariable)
                    .content(requestString)
                    .contentType(MediaType.APPLICATION_JSON);
        } else {
            builder = put(url, pathVariable)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url) {
        MockHttpServletRequestBuilder builder = get(url);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url, String pathVariable) {
        MockHttpServletRequestBuilder builder = get(url, pathVariable);

        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder getWrapper(String url, Object... pathVariables) {
        MockHttpServletRequestBuilder builder = get(url, pathVariables);
        return setCustomHeader(url, builder);
    }

    public static MockHttpServletRequestBuilder deleteWrapper(String url, String pathVariable) {
        MockHttpServletRequestBuilder builder = delete(url, pathVariable);

        return setCustomHeader(url, builder);
    }

    private static MockHttpServletRequestBuilder setCustomHeader(String url, MockHttpServletRequestBuilder builder) {
        builder.header("Endpoint", SCHEME + "://" + HOST + "/" + SERVICE_NAME);
        return builder.header(HttpHeaderConstants.X_SESSION_ID, "Session ID")
                .header(HttpHeaderConstants.X_MEMBER_ID, 1)
                .header(HttpHeaderConstants.X_CORRELATION_ID, "Correlation ID");
    }

    private static ParameterDescriptor setType(ParameterDescriptor parameterDescriptor, String type) {
        parameterDescriptor.attributes(new Attributes.Attribute("type", type));
        return parameterDescriptor;
    }

    public static ParameterDescriptor parameterWithNameAndType(@NotNull String name, @NotNull String type) {
        return setType(parameterWithName(name), type);
    }
}
