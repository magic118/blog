package com.newprj.blog.domain.search.service;

import com.newprj.blog.domain.search.dto.BlogSearchDto;
import com.newprj.blog.domain.search.dto.condition.BlogSearchCondition;

public interface BlogSearchService {
    BlogSearchDto findBlogSearchList(BlogSearchCondition condition);
}
