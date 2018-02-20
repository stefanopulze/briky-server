package com.stefano.briky.repository;

import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.model.Expenses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExpencesRepository extends JpaRepository<Expenses, Integer> {

    Expenses findByIdAndUserId(Integer id, Integer userId);

    @Query("select sum(value) from Expenses where userId=:userId and createdAt>=:startDate and createdAt<=:endDate")
    Double monthlySum(
            @Param("userId") Integer userId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );


    @Query("select e from Expenses e where e.userId=:userId order by e.createdAt desc")
    List<Expenses> findLast( @Param("userId") int userId, Pageable page);

    @Query("select e from Expenses e join e.tags tag where tag.id=:tagId order by e.createdAt desc")
    List<Expenses> findLastByTagId(@Param("tagId") int tagId, Pageable page);

    @Query("select sum(value) from Expenses where userId=:userId " +
            "and year(createdAt)=:#{#pagination.year} " +
            "and month(createdAt)=:#{#pagination.monthSql}")
    Integer monthlySum(@Param("userId") int userId, @Param("pagination") MonthFilter pagination);

    boolean existsByIdAndUserId(int id, int userId);
}