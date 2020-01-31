package com.freshworks.bookmarks.service;

import com.freshworks.bookmarks.dao.CategoryRepository;
import com.freshworks.bookmarks.dao.URLRepository;
import com.freshworks.bookmarks.dto.UrlDTO;
import com.freshworks.bookmarks.models.Category;
import com.freshworks.bookmarks.models.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class URLService {

  private static CategoryRepository categoryRepository;

  @Autowired
  private URLRepository urlRepository;

  @Autowired
  public URLService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  private static Logger logger = (Logger) LoggerFactory.getLogger(URLService.class);

  public UrlDTO createUrl(UrlDTO urlDTO, Integer categoryId) throws Exception {
    Assert.hasText(urlDTO.getUrlName(), "Url name cant be empty!!!");
    if (null == urlDTO.getUrlName()) {
      throw new Exception("Url Name is null!!");
    } else {
      URL createdUrl = null;
      Category cat = categoryRepository.findByCategoryId(categoryId);
      URL url = new URL();
      url.setUrlName(urlDTO.getUrlName());
      cat.getUrls().add(url);
      createdUrl = urlRepository.save(url);
      return new UrlDTO(createdUrl);
    }
  }

  @Transactional
  public UrlDTO updateUrl(UrlDTO urlDTO, Integer urlid) throws Exception {
    Assert.hasText(urlDTO.getUrlName(), "Url name cant be empty!!!");
    if (null == urlid) {
      throw new Exception("Url name is null");
    } else {
      URL url = urlRepository.findByUrlId(urlid);
      url.setUrlName(urlDTO.getUrlName());
      URL newUrl = urlRepository.save(url);
      return new UrlDTO(newUrl);
    }
  }

  public void deleteUrl(UrlDTO urlDTO, Integer categoryId) {
    Category category = categoryRepository.findByCategoryId(categoryId);
    List<URL> urls = category.getUrls();
    URL url = urlRepository.findByUrlName(urlDTO.getUrlName());
    logger.info(url.getUrlName());
    for (URL tempUrl : urls) {
      if (tempUrl.getUrlName() == url.getUrlName()) {
        urls.remove(url);
        break;
      }
    }
    categoryRepository.save(category);
  }
}
