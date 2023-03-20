package com.newprj.blog.domain.search.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_popular_search_list_history")
public class PopularSearchListHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=255, nullable = false)
    private String query;

    @Column(nullable = false)
    private Long count;

    @Builder
    public PopularSearchListHistory(String query, Long count) {
        this.query = query;
        this.count = count;
    }

}
