package com.freshworks.bookmarks.controller;

import com.freshworks.bookmarks.dao.CategoryRepository;
import com.freshworks.bookmarks.dto.CategoryDTO;
import com.freshworks.bookmarks.dto.UrlDTO;
import com.freshworks.bookmarks.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/api/categories")
public class CategoryController {

  final private String CATEGORY_NAME = "categoryName";

  @Autowired
  CategoryService categoryService;
  private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @Autowired
  CategoryRepository categoryRepository;


  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
    logger.info("Received request to create category " + categoryDTO.getCategoryName());
    if (categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())) {
      logger.error("Category already exist...!");
      return new ResponseEntity(categoryDTO, HttpStatus.CONFLICT);
    } else {
      categoryService.createCategory(categoryDTO);
      return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer id) throws Exception { // String categoryPayload
    logger.info("Received request to update category " + id);
    if (categoryRepository.existsByCategoryId(id)) {
      logger.info("Category exists...!");
      categoryService.updateCategory(categoryDTO, id);
      return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    } else {
      logger.info("Category not found");
      return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);

    }

  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity deleteCategory(@PathVariable Integer id) throws Exception {
    logger.info("receive request to delete category" + id);
    if (categoryRepository.existsByCategoryId(id)) {
      logger.info("Category found....!");
      categoryService.deleteCategory(id);
      return new ResponseEntity("Deleted", HttpStatus.OK);
    } else {
      logger.info("Category not found....can't delete...!!");
      return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
    }

  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    logger.info("Received request to get all categories");
    List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
    return new ResponseEntity(categoryDTOS, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}/urls", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<UrlDTO>> getAllUrls(@PathVariable Integer id) {
    logger.info("Received request to get all urls in category with ID:   " + id);
    if (categoryRepository.existsByCategoryId(id)) {
      logger.info("Category found....getting urls...");
      List<UrlDTO> urlDTOs = categoryService.getAllUrlsInCategory(id);
      return new ResponseEntity(urlDTOs, HttpStatus.OK);
    } else {
      logger.info("Category doesn't exists....");
      return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
    }


  }

}
