package com.newprj.blog.common.config;

import com.newprj.blog.common.constants.ExcludeLogUrl;
import lombok.extern.slf4j.Slf4j;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(factory);

        return restTemplateBuilder
                .requestFactory(() -> bufferingClientHttpRequestFactory)
                .setConnectTimeout(Duration.ofSeconds(5))
                .additionalInterceptors(new HttpLoggingInterceptor())
                .build();
    }

    @Bean
    public RestTemplate restTemplateLongTimeout(RestTemplateBuilder restTemplateBuilder) {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);

        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(factory);

        return restTemplateBuilder
                .requestFactory(() -> bufferingClientHttpRequestFactory)
                .setConnectTimeout(Duration.ofSeconds(5))
                .additionalInterceptors(new HttpLoggingInterceptor())
                .build();
    }
}

@Slf4j
class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        // Request 로깅
        if (log.isDebugEnabled()) {
            log.debug(
                    "===========================request begin================================================");
            log.debug("URI         : {}", request.getURI());
            log.debug("Method      : {}", request.getMethod());
            log.debug("Headers     : {}", request.getHeaders());
            log.debug("Request body: {}", new String(body, Charset.defaultCharset()));
            log.debug(
                    "==========================request end================================================");
        }

        ClientHttpResponse response = execution.execute(request, body);
        /**API에서 데이터가 많을 경우 로그가 너무 많이 찍혀 로그 확인이 어려운 문제로 로그제외 로직 추가
         * lockback(turboFilter)필터로 처리하려했으나 라인별 체크만 가능해서 직접 제외하는 방식으로 수정
         * */
        if (shouldExcludeUrl(request)) {
            return response;
        }
        // Response 로깅
        if (log.isDebugEnabled()) {
            log.debug(
                    "============================response begin==========================================");
            log.debug("Status code  : {}", response.getStatusCode());
            log.debug("Status text  : {}", response.getStatusText());
            log.debug("Headers      : {}", response.getHeaders());
            log.debug("Response body: {}",
                    StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            log.debug(
                    "=======================response end=================================================");
        }

        return response;
    }

    private boolean shouldExcludeUrl(HttpRequest request) {
        try {
            for (ExcludeLogUrl excludedUrl : ExcludeLogUrl.values()) {
                if (request.getURI().toString().contains(excludedUrl.getValue())) {
                    return true;
                }
            }

        } catch (Exception e) {
            log.error("shouldExcludeUrl error ==>" + e);
        }
        return false;
    }
}