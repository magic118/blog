package com.newprj.blog.domain.search.service;

import com.newprj.blog.domain.search.dto.BlogHistoryDto;
import com.newprj.blog.domain.search.dto.BlogSearchDto;
import com.newprj.blog.domain.search.dto.condition.BlogSearchCondition;

import java.util.List;

public interface BlogSearchService {
    BlogSearchDto findBlogSearchList(BlogSearchCondition condition);
    List<BlogHistoryDto> getTop10PopularHistorys();

}
