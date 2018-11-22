package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class ArticleMapper {

	private static final Logger logger = LoggerFactory.getLogger(ArticleMapper.class);

	public ArticleDto map(Article article) {

		logger.info("Logger Name: map() :: " + logger.getName());
		logger.debug("Article Details : map() :: " + article.toString());

		ArticleDto new_articleDto = null;
		try {
			new_articleDto = new ArticleDto();
			new_articleDto.setId(article.getId());
			new_articleDto.setTitle(article.getTitle());
			new_articleDto.setDescription(article.getDescription());
			new_articleDto.setAuthor(article.getAuthor());
			// for mapping and sorting the articles block
			 /*Set articleBlocks = new HashSet();
			  articleBlocks = article.getBlocks(); 
			  Collection<ArticleBlockDto> articleDtoBlocks = new TreeSet<ArticleBlockDto>(); 
			  List<ArticleBlockDto>  articleDtoBlocks = new ArrayList<ArticleBlockDto>();
			  articleDtoBlocks.addAll(articleBlocks); Collections.sort(articleDtoBlocks, new SortBySortIndex()); 
			  new_articleDto.setBlocks(articleDtoBlocks);*/
			 
		} catch (Exception e) {
			logger.error("Exception : map() :: ", e);
		}

		logger.info("Return value from Mapper layer :: map() :: " + new_articleDto.toString());
		return new_articleDto;
	}

	public Article map(ArticleDto articleDto) {
		// Nicht Teil dieser Challenge.
		return new Article();
	}
}
