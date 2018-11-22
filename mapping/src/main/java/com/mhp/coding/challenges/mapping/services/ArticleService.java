package com.mhp.coding.challenges.mapping.services;

import com.mhp.coding.challenges.mapping.mappers.ArticleMapper;
import com.mhp.coding.challenges.mapping.mappers.NoArticleFoundException;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
	private final ArticleRepository repository;

	private final ArticleMapper mapper;

	@Autowired
	public ArticleService(ArticleRepository repository, ArticleMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<ArticleDto> list() {
		final List<Article> articles = repository.all();
		logger.debug("Total number of Article : list() :: " + articles.size());
		final List<ArticleDto> articleDtoList = new ArrayList<ArticleDto>();
		try {
			for (int article_position = 0; article_position < articles.size(); article_position++) {
				ArticleDto articleDto_Object = mapper.map(articles.get(article_position));
				articleDtoList.add(articleDto_Object);
			}

		} catch (Exception e) {
			logger.error("Exception : list() :: ", e);
		}
		logger.info("Return value from Service layer :: list() :: " + articleDtoList.toString());
		return articleDtoList;
	}

	public ArticleDto articleForId(Long id) throws Exception {
		logger.debug("Article Id : articleForId() :: " + id);
		boolean flag = false;
		ArticleDto articleDto_Object = null;
		try {
			final Article article = repository.findBy(id);
			final List<Article> articles = repository.all();
			for (int article_position = 0; article_position < articles.size(); article_position++) {
				if (articles.get(article_position).getId().equals(id)) {
					articleDto_Object = mapper.map(articles.get(article_position));
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Exception : articleForId() :: ", e);
		}
		logger.debug("flag value : articleForId() :: " + flag);
		if (flag == false) {
			throw new NoArticleFoundException();
		}

		logger.info("Return value from Service layer :: articleForId() :: " + articleDto_Object.toString());
		return articleDto_Object;
	}

	public ArticleDto create(ArticleDto articleDto) {
		final Article create = mapper.map(articleDto);
		repository.create(create);
		return mapper.map(create);
	}
}
