package com.genius.gitget.admin.topic.dto;

import com.genius.gitget.admin.topic.domain.Topic;
import com.genius.gitget.global.file.dto.FileResponse;
import lombok.Builder;

@Builder
public record TopicDetailResponse(
        Long topicId,
        String title,
        String tags,
        String description,
        String notice,
        int pointPerPerson,
        FileResponse fileResponse) {
    public static TopicDetailResponse createByEntity(Topic topic, FileResponse fileResponse) {
        return TopicDetailResponse.builder()
                .topicId(topic.getId())
                .title(topic.getTitle())
                .tags(topic.getTags())
                .description(topic.getDescription())
                .notice(topic.getNotice())
                .pointPerPerson(topic.getPointPerPerson())
                .fileResponse(fileResponse)
                .build();
    }
}
