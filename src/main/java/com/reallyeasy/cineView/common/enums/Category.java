package com.reallyeasy.cineView.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    INFO("정보 게시판"),
    FREE("자유 게시판"),
    SUGGEST("건의/문의 게시판");

    private final String displayName;
}