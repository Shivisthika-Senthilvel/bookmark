package com.freshworks.bookmarks.dao;

import com.freshworks.bookmarks.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  public List<Category> findAll();

  public Category findByCategoryName(String categoryName);

  boolean existsByCategoryName(String categoryName);


  public Category findByCategoryId(Integer categoryId);

  boolean existsByCategoryId(Integer categoryId);
}
