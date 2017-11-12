package com.stefano.briky.repository;

import com.stefano.briky.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tags, Integer> {

    @Query("select tag from Users u join u.tags tag where u.id=:userId and tag.name=:tagName")
    Tags findByName(@Param("tagName") String name, @Param("userId") Integer userId);

    @Query("select tag from Users u join u.tags tag where u.id=:userId")
    List<Tags> findForUser(@Param("userId") int userId);
}
