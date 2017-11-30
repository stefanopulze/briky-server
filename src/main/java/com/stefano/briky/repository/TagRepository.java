package com.stefano.briky.repository;

import com.stefano.briky.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tags, Integer> {

    @Query("select tag from Tags tag where tag.slug=:slug and tag.userId=:userId")
    Tags findBySlugAndUser(@Param("slug") String slug,  @Param("userId") Integer userId);

    List<Tags> findByUserId(int userId);

    @Query("select count(tag.id) from Expenses e join e.tags tag where tag.id = :tagId")
    Integer countById(@Param("tagId") int tagId);
}
