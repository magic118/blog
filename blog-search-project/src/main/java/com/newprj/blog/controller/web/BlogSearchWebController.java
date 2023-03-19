package com.newprj.blog.controller.web;

import com.newprj.blog.domain.search.dto.BlogSearchDto;
import com.newprj.blog.domain.search.dto.condition.BlogSearchCondition;
import com.newprj.blog.domain.search.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogSearchWebController {

    private final BlogSearchService blogSearchService;

    @GetMapping("/search")
    public String getBlogSearch(@PageableDefault(page = 0, size = 10, sort = "searchDtm", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(value = "query", defaultValue = "kakaobank") String query,
                                @RequestParam(value = "sort_item", defaultValue = "accuracy") final String sortItem,
                                @RequestParam(value = "page", defaultValue = "1") final int page,
                                @RequestParam(value = "size", defaultValue = "10") final int size,
                                Model model) {


        BlogSearchCondition condition = new BlogSearchCondition();
        condition.setQuery(query);
        condition.setSortItem(sortItem);
        condition.setPage(page);
        condition.setSize(size);
        condition.setLimit(10);

        BlogSearchDto blogSearchDto = blogSearchService.findBlogSearchList(condition);

        int pageSize = 10;
        int total = blogSearchDto.getPageInfo().getPageableCount(); // 총 문서 수
        int totalPages = (int)Math.ceil((double)total/pageSize); // 전체 페이지 수
        int startPage = Math.max(1, page-5); // 시작 페이지
        int endPage = Math.min(totalPages, page+4); // 끝 페이지


        model.addAttribute("condition", condition);
        model.addAttribute("pageInfo", blogSearchDto.getPageInfo());
        model.addAttribute("blogInfoList", blogSearchDto.getBlogInfoList());

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", page);

        return "blog/blog_search";
    }

}
