package com.genius.gitget.admin.topic.controller;

import static com.genius.gitget.global.util.exception.SuccessCode.CREATED;
import static com.genius.gitget.global.util.exception.SuccessCode.SUCCESS;

import com.genius.gitget.admin.topic.dto.TopicCreateRequest;
import com.genius.gitget.admin.topic.dto.TopicDetailResponse;
import com.genius.gitget.admin.topic.dto.TopicIndexResponse;
import com.genius.gitget.admin.topic.dto.TopicPagingResponse;
import com.genius.gitget.admin.topic.dto.TopicUpdateRequest;
import com.genius.gitget.admin.topic.service.TopicService;
import com.genius.gitget.global.util.response.dto.CommonResponse;
import com.genius.gitget.global.util.response.dto.PagingResponse;
import com.genius.gitget.global.util.response.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/topic")
public class TopicController {

    private final TopicService topicService;

    // 토픽 리스트 요청
    @GetMapping
    public ResponseEntity<PagingResponse<TopicPagingResponse>> getAllTopics(
            @PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TopicPagingResponse> allTopics = topicService.getAllTopics(pageable);

        return ResponseEntity.ok().body(
                new PagingResponse<>(SUCCESS.getStatus(), SUCCESS.getMessage(), allTopics)
        );
    }

    // 토픽 상세 정보 요청
    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<TopicDetailResponse>> getTopicById(@PathVariable Long id) {
        TopicDetailResponse topicDetail = topicService.getTopicById(id);
        return ResponseEntity.ok().body(
                new SingleResponse<>(SUCCESS.getStatus(), SUCCESS.getMessage(), topicDetail)
        );
    }

    // 토픽 생성 요청
    @PostMapping
    public ResponseEntity<SingleResponse<TopicIndexResponse>> createTopic(
            @RequestBody TopicCreateRequest topicCreateRequest) {
        Long topicId = topicService.createTopic(topicCreateRequest);
        TopicIndexResponse topicUpdateResponse = new TopicIndexResponse(topicId);

        return ResponseEntity.ok().body(
                new SingleResponse<>(
                        CREATED.getStatus(), CREATED.getMessage(), topicUpdateResponse)
        );
    }

    // 토픽 수정 요청
    @PatchMapping("/{id}")
    public ResponseEntity<SingleResponse<TopicIndexResponse>> updateTopic(
            @PathVariable Long id,
            @RequestBody TopicUpdateRequest topicUpdateRequest) {
        Long topicId = topicService.updateTopic(id, topicUpdateRequest);
        TopicIndexResponse topicUpdateResponse = new TopicIndexResponse(topicId);

        return ResponseEntity.ok().body(
                new SingleResponse<>(SUCCESS.getStatus(), SUCCESS.getMessage(), topicUpdateResponse)
        );
    }

    // 토픽 삭제 요청
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok().body(
                new CommonResponse(SUCCESS.getStatus(), SUCCESS.getMessage())
        );
    }
}