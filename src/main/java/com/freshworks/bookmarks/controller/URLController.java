package com.freshworks.bookmarks.controller;

import com.freshworks.bookmarks.dao.CategoryRepository;
import com.freshworks.bookmarks.dao.URLRepository;
import com.freshworks.bookmarks.dto.UrlDTO;
import com.freshworks.bookmarks.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/urls")

public class URLController {
  final private String urlId = "urlId";
  final private String urlName = "urlName";
  final private String categoryId = "categoryId";
  final private String categoryName = "categoryName";

  private static Logger logger = LoggerFactory.getLogger(URLController.class);


  @Autowired
  URLService urlService;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  URLRepository urlRepository;


  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<UrlDTO> createUrl(@RequestBody UrlDTO urlDTO, @PathVariable Integer id) throws Exception {
    logger.info("Received request to create URL  " + urlDTO.getUrlName() + "under category  " + categoryId);
    if (categoryRepository.existsByCategoryId(id)) {
      logger.info("Category found....");
      if (urlRepository.existsByUrlName(urlDTO.getUrlName())) {
        logger.info("Url already exists...can't add...");
        return new ResponseEntity<UrlDTO>(urlDTO, HttpStatus.CONFLICT);
      } else {
        logger.info("Adding url...");
        urlService.createUrl(urlDTO, id);
        return new ResponseEntity<UrlDTO>(urlDTO, HttpStatus.CREATED);
      }
    } else {
      logger.info("Category doesn't exists....");
      return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
    }
  }


  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity updateUrl(@RequestBody UrlDTO urlDTO, @PathVariable Integer id) throws Exception {
    logger.info("Received request to update URL  " + urlDTO.getUrlName());
    if (urlRepository.existsByUrlId(id)) {
      logger.info("Url found....updating url...");
      urlService.updateUrl(urlDTO, id);
      return new ResponseEntity<UrlDTO>(urlDTO, HttpStatus.OK);
    } else {
      logger.info("Url doesn't exists....");
      return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity deleteUrl(@RequestBody UrlDTO urlDTO, @PathVariable Integer id) {
    logger.info("Received request to delete url in the category with id: " + categoryId);
    if (categoryRepository.existsByCategoryId(id)) {
      logger.info("Category found...deleting url.....");
      urlService.deleteUrl(urlDTO, id);
      return new ResponseEntity<UrlDTO>(urlDTO, HttpStatus.OK);
    } else {
      logger.info("Category not found....");
      return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
    }
  }

}