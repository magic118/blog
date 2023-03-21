package com.newprj.blog.controller.web;

import com.newprj.blog.domain.search.dto.BlogSearchDto;
import com.newprj.blog.domain.search.dto.condition.BlogSearchCondition;
import com.newprj.blog.domain.search.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String getBlogSearch(@RequestParam(value = "query", defaultValue = "kakaobank") String query,
                                @RequestParam(value = "sort", defaultValue = "accuracy") String sortItem,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                Model model) {


        BlogSearchCondition condition = new BlogSearchCondition();
        condition.setQuery(query);
        condition.setSortItem(sortItem);
        condition.setPage(page);
        condition.setSize(10);

        log.debug("query {} sort_item {} page{} size {}",
                                            condition.getQuery(),
                                            condition.getSortItem(),
                                            condition.getPage(),
                                            condition.getSize());

        BlogSearchDto blogSearchDto = blogSearchService.findBlogSearchList(condition);

        int total = blogSearchDto.getPageInfo().getPageableCount(); // 총 문서 수
        int totalPages = (int)Math.ceil((double)total/condition.getSize()); // 전체 페이지 수
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
