package com.newprj.blog.domain.search.repository;

import com.newprj.blog.domain.search.dto.BlogHistoryDto;
import com.newprj.blog.domain.search.entity.PopularSearchListHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PopularSearchListHistoryRepository extends JpaRepository<PopularSearchListHistory, Long> {

    Optional<PopularSearchListHistory> findByQuery(String query);
    List<PopularSearchListHistory> findTop10ByOrderByCountDesc();

}

