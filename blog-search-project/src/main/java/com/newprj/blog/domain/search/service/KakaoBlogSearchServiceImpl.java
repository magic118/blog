package com.newprj.blog.domain.search.service;

import com.newprj.blog.common.exception.InternalServerException;
import com.newprj.blog.controller.web.model.BlogSearchResponse;
import com.newprj.blog.domain.search.dto.BlogSearchDto;
import com.newprj.blog.domain.search.dto.condition.BlogSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoBlogSearchServiceImpl implements BlogSearchService {
    private final RestTemplate restTemplate;

    @Value("${spring.blog.kakao.restapi.host}")
    private String host;

    @Value("${spring.blog.kakao.restapi.uri}")
    private String uri;

    @Value("${spring.blog.kakao.restapi.key}")
    private String restApiKey;

    public BlogSearchDto findBlogSearchList(BlogSearchCondition condition) {

        final String baseUri = host + uri;
        final String headerValue = "KakaoAK " + restApiKey;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseUri)
                .queryParam("query", condition.getQuery())
                .queryParam("sort", condition.getSortItem())
                .queryParam("page", condition.getPage())
                .queryParam("size", condition.getSize());


        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization",headerValue);

        HttpEntity<?> request = new HttpEntity<>(headers);

        log.debug(uriBuilder.toUriString());

        // API 호출 후 값 설정
        ResponseEntity <BlogSearchResponse> result = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, request, BlogSearchResponse.class);
        if (result.getBody() == null || !result.getStatusCode().is2xxSuccessful()) {
            throw new InternalServerException();
        }

        BlogSearchDto blogSearchDto = new BlogSearchDto();
        BlogSearchDto.pageInfo pageInfo = new BlogSearchDto.pageInfo();
        List<BlogSearchDto.BlogInfo>  blogInfoList = new ArrayList<>();

        pageInfo.setTotalCount(Objects.requireNonNull(result.getBody()).getMeta().getTotalCount());
        pageInfo.setPageableCount(result.getBody().getMeta().getPageableCount());
        pageInfo.setIsEnd(result.getBody().getMeta().getIsEnd());

        blogSearchDto.setPageInfo(pageInfo);

        List<BlogSearchResponse.Documents> documents = Objects.requireNonNull(result.getBody()).getDocuments();

        for(BlogSearchResponse.Documents document : documents) {
            BlogSearchDto.BlogInfo blogInfo = new BlogSearchDto.BlogInfo()
                    .setBlogName(document.getBlogname())
                    .setContents(document.getContents())
                    .setTitle(document.getTitle())
                    .setUrl(document.getUrl())
                    .setThumbnail(document.getThumbnail())
                    .setDateTime(document.getDatetime());

            blogInfoList.add(blogInfo);
        }
        blogSearchDto.setBlogInfoList(blogInfoList);

        return blogSearchDto;
    }
}
