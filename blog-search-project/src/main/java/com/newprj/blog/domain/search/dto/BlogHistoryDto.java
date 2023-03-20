package com.newprj.blog.domain.search.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BlogHistoryDto {
        private String query;
        private Long count;
}
