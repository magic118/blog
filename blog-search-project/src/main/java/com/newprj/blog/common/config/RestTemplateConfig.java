package com.newprj.blog.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
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
}