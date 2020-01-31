package com.freshworks.bookmarks.service;

import com.freshworks.bookmarks.dao.CategoryRepository;
import com.freshworks.bookmarks.dao.URLRepository;
import com.freshworks.bookmarks.dto.CategoryDTO;
import com.freshworks.bookmarks.dto.UrlDTO;
import com.freshworks.bookmarks.models.Category;
import com.freshworks.bookmarks.models.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  URLRepository urlRepository;

  private Logger logger = LoggerFactory.getLogger(CategoryService.class);

  @Transactional
  public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {
    Assert.hasText(categoryDTO.getCategoryName(), "Category name cannot be blank!!!!");
    Assert.hasText(categoryDTO.getCategoryDescription(), "Category description cannot be blank!!!!");
    if (null == categoryDTO.getCategoryName() && null == categoryDTO.getCategoryDescription())
      throw new Exception("Category creation failed....got a null value");
    else {
      Category createdCategory = null;
      logger.info("creating category");
      Category category = new Category();
      category.setCategoryName(categoryDTO.getCategoryName());
      category.setCategoryDescription(categoryDTO.getCategoryDescription());
      category.setCreatedAt(new Date());
      createdCategory = categoryRepository.save(category);
      return new CategoryDTO(createdCategory);
    }
  }

  @Transactional
  public void deleteCategory(Integer categoryId) throws Exception {
    Assert.notNull(categoryId, "CategoryId cannot be null");
    if (null == categoryId) {
      throw new Exception("Can't delete null category");
    } else {
      Category category = categoryRepository.findByCategoryId(categoryId);
      categoryRepository.delete(category);
    }
  }

  @Transactional
  public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) throws Exception {
    Assert.hasText(categoryDTO.getCategoryName(), "Category Name is blank!!!");
    if (null == categoryId) {
      throw new Exception("Can't update null category!!!!");
    } else {
      Category category = categoryRepository.findByCategoryId(categoryId);
      category.setCategoryName(categoryDTO.getCategoryName());
      category.setUpdatedAt(new Date());
      Category newCat = categoryRepository.save(category);
      return new CategoryDTO(newCat);
    }
  }


  @Transactional
  public List<CategoryDTO> getAllCategories() {
    logger.info("Getting all Categories.");
    List<Category> categoryList = categoryRepository.findAll();
    List<CategoryDTO> categoryDTOS = new ArrayList<>(4);
    for (Category category : categoryList) {
      categoryDTOS.add(new CategoryDTO((category)));
    }
    return categoryDTOS;
  }

  @Transactional
  public List<UrlDTO> getAllUrlsInCategory(Integer categoryId) {
    Category category = categoryRepository.findByCategoryId(categoryId);
    List<URL> urls = category.getUrls();
    List<UrlDTO> urlDTOS = new ArrayList<>(4);
    for (URL url : urls) {
      urlDTOS.add(new UrlDTO(url));
    }
    return urlDTOS;
  }
}


