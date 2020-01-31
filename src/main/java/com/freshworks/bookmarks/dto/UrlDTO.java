package com.freshworks.bookmarks.dto;

import com.freshworks.bookmarks.models.Category;
import com.freshworks.bookmarks.models.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UrlDTO extends URL {

  private Integer urlId;

  private String urlName;


  private Date createdAt;

  private List<Category> categories;

  private Date updatedAt;

  private List<URL> urls = new ArrayList<>();

  public UrlDTO() {

  }

  public UrlDTO(URL url) {
    this.urlId = url.getUrlId();
    this.urlName = url.getUrlName();
    this.createdAt = url.getLastUpdatedAt();
    this.updatedAt = url.getLastUpdatedAt();
  }


  public Integer getUrlId() {
    return urlId;
  }

  public void setUrlId(Integer urlId) {
    this.urlId = urlId;
  }

  public String getUrlName() {
    return urlName;
  }

  public void setUrlName(String urlName) {
    this.urlName = urlName;
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

  private List<UrlDTO> UrlDTO;
  private List<CategoryDTO> categoryDTO;


}
