package com.magomed.a.service;

import com.magomed.a.data.ArticleDto;
import com.magomed.a.mapper.ArtAndOrdMapper;
import com.magomed.a.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArtAndOrdMapper artAndOrdMapper;

    public List<ArticleDto> getAll() {
        return articleRepository.findAll()
                .stream().map(artAndOrdMapper::articleEntityToDto).toList();
    }

    public ArticleDto getById(int id) {
        return artAndOrdMapper.articleEntityToDto(articleRepository.findById(id).get());
    }
}
