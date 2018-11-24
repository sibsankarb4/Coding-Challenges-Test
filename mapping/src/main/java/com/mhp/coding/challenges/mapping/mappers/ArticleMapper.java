
package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.GalleryBlock;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


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
			new_articleDto.setBlocks(getSortedArticleBlocksDto(article));
			/* below code blcok is for mapping and sorting the articles block */
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
	
	 private Set<ArticleBlockDto> getSortedArticleBlocksDto(Article article) throws Exception {     
	 logger.info("Article :: getSortedArticleBlocksDto() :: " + article.toString());
         return article.getBlocks().stream().map(this::mapArticleBlockDto_new)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(ArticleBlockDto::getSortIndex))));
	    }
	
	private ArticleBlockDto mapArticleBlockDto_new(ArticleBlock block) throws Exception{
	   logger.info("Block Details :: mapArticleBlockDto_new() :: " + block.toString());
	    if (block instanceof GalleryBlock) {
            final GalleryBlock galleryBlock = (GalleryBlock) block;
            GalleryBlockDto galleryBlockDto = new GalleryBlockDto();
            galleryBlockDto.setImages(galleryBlock.getImages().stream().map(this::mapImage).collect(Collectors.toList()));
            galleryBlockDto.setSortIndex(galleryBlock.getSortIndex());
	    logger.info("Returning galleryBlockDto from Mapper layer :: mapArticleBlockDto_new() :: " + galleryBlockDto.toString());
            return galleryBlockDto;
        }

        if (block instanceof com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock) {
            final com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock imageBlock = (com.mhp.coding.challenges.mapping.models.db.blocks.ImageBlock) block;
            ImageBlock imageBlockDto = new ImageBlock();
            imageBlockDto.setImage(mapImageBlock(imageBlock.getImage()));
            imageBlockDto.setSortIndex(imageBlock.getSortIndex());
	    logger.info("Returning imageBlockDto from Mapper layer :: mapArticleBlockDto_new() :: " + imageBlockDto.toString());
            return imageBlockDto;
        }
        if (block instanceof com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock) {
            final com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock textBlock = (com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock) block;
            TextBlock textBlockDto = new TextBlock();
            textBlockDto.setText(textBlock.getText());
            textBlockDto.setSortIndex(textBlock.getSortIndex());
	    logger.info("Returning textBlockDto from Mapper layer :: mapArticleBlockDto_new() :: " + textBlockDto.toString());
            return textBlockDto;
        }
        if (block instanceof com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock) {
            final com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock videoBlock = (com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock) block;
            VideoBlock videoBlockDto = new VideoBlock();
            videoBlockDto.setType(videoBlock.getType());
            videoBlockDto.setUrl(videoBlock.getUrl());
            videoBlockDto.setSortIndex(videoBlock.getSortIndex());
	    logger.info("Returning videoBlockDto from Mapper layer :: mapArticleBlockDto_new() :: " + videoBlockDto.toString());
            return videoBlockDto;
        }
        ArticleBlockDto articleBlockDto = new ArticleBlockDto();
        articleBlockDto.setSortIndex(block.getSortIndex());
	logger.info("Returning articleBlockDto from Mapper layer :: mapArticleBlockDto_new() :: " + articleBlockDto.toString());
        return articleBlockDto;		
	}
	
	private ImageDto mapImageBlock(Image image) throws Exception{
	logger.info("Image Details :: mapImageBlock() :: " + image.toString());
        final ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setUrl(image.getUrl());
        imageDto.setImageSize(image.getImageSize());
	logger.info("Returning imageDto from Mapper layer :: mapImageBlock() :: " + imageDto.toString());
        return imageDto;
    }	

	public Article map(ArticleDto articleDto) {
		// Nicht Teil dieser Challenge.
		return new Article();
	}
}
