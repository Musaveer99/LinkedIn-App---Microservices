package com.posts_service.dto;

import lombok.Data;

@Data
public class PostCreateRequestDto {

    private String content;

    public PostCreateRequestDto(String content) {
        this.content = content;
    }

    public PostCreateRequestDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
