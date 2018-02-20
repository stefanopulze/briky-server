package com.stefano.briky.repository;

import com.stefano.briky.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tags, Integer> {

    List<Tags> findByUserIdOrderByName(int userId);

    @Query("select count(tag.id) from Expenses e join e.tags tag where tag.id = :tagId")
    Integer countById(@Param("tagId") int tagId);

    Tags findBySlugAndUserId(String slug, int userId);
}
