package com.freshworks.bookmarks.dao;

import com.freshworks.bookmarks.dto.UrlDTO;
import com.freshworks.bookmarks.models.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepository extends JpaRepository<URL, Integer> {
    boolean existsByUrlName(String urlName);

    public URL findByUrlName(String urlName);

    boolean existsByUrlId(Integer urlid);

    URL findByUrlId(Integer urlid);
}

