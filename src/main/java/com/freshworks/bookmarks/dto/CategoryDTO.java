package com.freshworks.bookmarks.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freshworks.bookmarks.models.Category;
import com.freshworks.bookmarks.models.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryDTO {


  private Integer categoryId;

  private String categoryName;

  private String categoryDescription;

  private Date createdAt;

  private Date updatedAt;

  public CategoryDTO() {
  }

  private List<Category> categoryList = new ArrayList<>();

  private List<CategoryDTO> categoryDTOS;
  private Category category;

  public List<URL> getUrls() {
    return urls;
  }

  public void setUrls(List<URL> urls) {
    this.urls = urls;
  }

  private List<URL> urls;

  public CategoryDTO(List<URL> urls) {
    this.urls = urls;
  }

  public CategoryDTO(Category category) {
    this.categoryId = category.getCategoryId();// categoryId;
    this.categoryName = category.getCategoryName();
    this.categoryDescription = category.getCategoryDescription();
    this.createdAt = category.getCreatedAt();
    this.updatedAt = category.getUpdatedAt();

  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryDescription() {
    return categoryDescription;
  }

  public void setCategoryDescription(String categoryDescription) {
    this.categoryDescription = categoryDescription;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setCategories(CategoryDTO categoryDTO) {
    categoryList.add(category);
  }

}



