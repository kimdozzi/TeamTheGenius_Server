package com.genius.gitget.global.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_UPDATED(HttpStatus.BAD_REQUEST, "유저 정보가 업데이트되지 않았습니다."),
    LIKES_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 좋아요 목록을 찾을 수 없습니다"),
    FAILED_POINT_PAYMENT(HttpStatus.BAD_REQUEST, "최소 충전 금액은 100원 이상입니다."),
    INVALID_PAYMENT_AMOUNT(HttpStatus.BAD_REQUEST, "최초 결제 요청 금액과 일치하지 않습니다."),
    FAILED_FINAL_PAYMENT(HttpStatus.BAD_REQUEST, "최종 결제가 승인되지 않았습니다"),
    INVALID_ORDERID(HttpStatus.NOT_FOUND, "해당 주문번호가 존재하지 않습니다."),

    TOPIC_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 토픽을 찾을 수 없습니다."),
    TOPIC_HAVE_INSTANCE(HttpStatus.BAD_REQUEST, "해당 토픽은 인스턴스를 가지고 있으므로 삭제할 수 없습니다."),

    INSTANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 인스턴스를 찾을 수 없습니다."),

    ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 회원가입이 완료된 사용자입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다"),
    NOT_AUTHENTICATED_USER(HttpStatus.BAD_REQUEST, "인증 가능한 사용자가 아닙니다."),

    INVALID_EXPIRED_JWT(HttpStatus.BAD_REQUEST, "이미 만료된 JWT 입니다."),
    INVALID_MALFORMED_JWT(HttpStatus.BAD_REQUEST, "JWT의 구조가 유효하지 않습니다."),
    INVALID_CLAIM_JWT(HttpStatus.BAD_REQUEST, "JWT의 Claim이 유효하지 않습니다."),
    UNSUPPORTED_JWT(HttpStatus.BAD_REQUEST, "지원하지 않는 JWT 형식입니다."),
    INVALID_JWT(HttpStatus.BAD_REQUEST, "JWT가 유효하지 않습니다."),
    INVALID_PROGRESS(HttpStatus.BAD_REQUEST, "존재하지 않는 정보입니다."),

    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Cookie에 토큰이 존재하지 않습니다."),

    FILE_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 파일(이미지)이 존재하지 않습니다."),
    NOT_SUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "지원하지 않는 확장자입니다."),
    NOT_SUPPORTED_IMAGE_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 타입입니다."),
    IMAGE_NOT_ENCODED(HttpStatus.BAD_REQUEST, "이미지를 인코딩하는 과정에서 오류가 발생했습니다."),
    FILE_NOT_SAVED(HttpStatus.BAD_REQUEST, "파일(이미지)가 정상적으로 저장되지 않았습니다."),
    FILE_NOT_COPIED(HttpStatus.BAD_REQUEST, "파일(이미지)가 정상적으로 복사되지 않았습니다."),
    FILE_NOT_DELETED(HttpStatus.BAD_REQUEST, "파일(이미지)이 정상적으로 삭제되지 않았습니다.");


    private final HttpStatus status;
    private final String message;
}
