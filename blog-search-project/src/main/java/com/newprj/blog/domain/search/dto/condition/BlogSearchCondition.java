package com.newprj.blog.domain.search.dto.condition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogSearchCondition {
    private String query;

    private int size;
    private String sortItem;
    private int page;
}
