package com.newprj.blog.domain.search.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BlogSearchDto {

    private BlogSearchDto.pageInfo pageInfo;
    private List<BlogInfo> blogInfoList;

    @NoArgsConstructor
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class pageInfo {
        private int totalCount;
        private int pageableCount;
        private Boolean isEnd;

        private int page;
        private int count;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class BlogInfo {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        private String dateTime;
    }
}
