package com.magomed.a.repository;

import com.magomed.a.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    List<ArticleEntity> findAllByNameIn(List<String> names);

    Optional<ArticleEntity> findByName(String name);
}
