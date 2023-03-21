package com.newprj.blog.controller.web;

import com.newprj.blog.domain.search.dto.BlogHistoryDto;
import com.newprj.blog.domain.search.service.BlogSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogHistoryWebController {
    private final BlogSearchService blogSearchService;

    @GetMapping("/history")
    public String getBlogHistory(Model model) {
        List<BlogHistoryDto> blogHistoryDtoList = blogSearchService.getTop10PopularHistorys();

        model.addAttribute("blogHistoryDtoList", blogHistoryDtoList);
        return "blog/blog_history";
    }
}
