package com.genius.gitget.topic.dto;

public record TopicDetailResponse(
        Long topicId,
        String title,
        String tags,
        String description,
        // 이미지
        // 유의사항
        int pointPerPerson
) {
}