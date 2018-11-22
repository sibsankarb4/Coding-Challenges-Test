package com.mhp.coding.challenges.mapping.mappers;

import java.util.Comparator;

import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

public class SortBySortIndex implements Comparator<ArticleBlockDto> {

	// Used for sorting in ascending order of sort index number
	@Override
	public int compare(ArticleBlockDto articleBlockDto_obj1, ArticleBlockDto articleBlockDto_obj2) {
		return articleBlockDto_obj1.getSortIndex() - articleBlockDto_obj2.getSortIndex(); 
	}

}
